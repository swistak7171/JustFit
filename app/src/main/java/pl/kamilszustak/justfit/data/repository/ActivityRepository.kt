package pl.kamilszustak.justfit.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.kamilszustak.justfit.common.data.NetworkBoundResource
import pl.kamilszustak.justfit.common.data.NetworkCall
import pl.kamilszustak.justfit.common.data.Resource
import pl.kamilszustak.justfit.data.database.dao.ActivityDao
import pl.kamilszustak.justfit.data.database.dao.ActivityEquipmentDao
import pl.kamilszustak.justfit.data.database.dao.EquipmentDao
import pl.kamilszustak.justfit.domain.mapper.EquipmentJsonMapper
import pl.kamilszustak.justfit.domain.mapper.activity.ActivityJsonMapper
import pl.kamilszustak.justfit.domain.model.activity.ActivityEquipmentCrossReference
import pl.kamilszustak.justfit.domain.model.activity.ActivityJson
import pl.kamilszustak.justfit.domain.model.activity.ActivityWithEquipment
import pl.kamilszustak.justfit.network.model.ClientActivityJson
import pl.kamilszustak.justfit.network.model.JoinInActivityRequestBody
import pl.kamilszustak.justfit.network.service.ActivityApiService
import pl.kamilszustak.justfit.network.service.ClientApiService
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActivityRepository @Inject constructor(
    private val activityDao: ActivityDao,
    private val equipmentDao: EquipmentDao,
    private val userDetailsRepository: UserDetailsRepository,
    private val activityEquipmentDao: ActivityEquipmentDao,
    private val activityApiService: ActivityApiService,
    private val clientApiService: ClientApiService,
    private val activityJsonMapper: ActivityJsonMapper,
    private val equipmentJsonMapper: EquipmentJsonMapper
) {
    fun getAll(shouldFetch: Boolean): Flow<Resource<List<ActivityWithEquipment>>> {
        return object : NetworkBoundResource<List<ActivityJson>, List<ActivityWithEquipment>>() {
            override fun loadFromDatabase(): Flow<List<ActivityWithEquipment>> =
                activityDao.getAll()

            override fun shouldFetch(data: List<ActivityWithEquipment>?): Boolean =
                shouldFetch

            override suspend fun fetchFromNetwork(): Response<List<ActivityJson>> =
                activityApiService.getAll()

            override suspend fun saveFetchResult(result: List<ActivityJson>) {
                result.forEach { activity ->
                    equipmentJsonMapper.onMapAll(activity.usedEquipment) { equipment ->
                        equipmentDao.insertAll(equipment)
                    }

                    val activityEquipment = activity.usedEquipment.map { equipment ->
                        ActivityEquipmentCrossReference(
                            activityId = activity.id,
                            equipmentId = equipment.id
                        )
                    }

                    activityEquipmentDao.replaceAllByActivityId(activityEquipment)
                }

                activityJsonMapper.onMapAll(result) { activities ->
                    activityDao.replaceAll(activities)
                }
            }
        }.asFlow()
    }

    fun getById(id: Long, shouldFetch: Boolean): Flow<Resource<ActivityWithEquipment>> {
        return object : NetworkBoundResource<ActivityJson, ActivityWithEquipment>() {
            override fun loadFromDatabase(): Flow<ActivityWithEquipment> =
                activityDao.getById(id)

            override fun shouldFetch(data: ActivityWithEquipment?): Boolean =
                shouldFetch

            override suspend fun fetchFromNetwork(): Response<ActivityJson> =
                activityApiService.getById(id)

            override suspend fun saveFetchResult(result: ActivityJson) {
                equipmentJsonMapper.onMapAll(result.usedEquipment) { equipment ->
                    equipmentDao.insertAll(equipment)
                }

                val activityEquipment = result.usedEquipment.map { equipment ->
                    ActivityEquipmentCrossReference(
                        activityId = result.id,
                        equipmentId = equipment.id
                    )
                }

                activityEquipmentDao.replaceAllByActivityId(activityEquipment)

                activityJsonMapper.onMap(result) { activity ->
                    activityDao.insert(activity)
                }
            }
        }.asFlow()
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun getAllJoinedInByClient(): Flow<Resource<List<ActivityWithEquipment>>> {
        return flow {
            emit(Resource.loading(null))
            val response = clientApiService.getAllClientActivities()
            val body = response.body()
            if (!response.isSuccessful || body == null) {
                emit(Resource.error("Unsuccessful API request", null))
                return@flow
            }

            val activitiesIds = body.map(ClientActivityJson::activityId)
            val activities = buildList<ActivityWithEquipment> {
                activitiesIds.forEach { id ->
                    val alreadyFetchedActivity = this.find { it.activity.id == id }
                    if (alreadyFetchedActivity != null) {
                        return@forEach
                    }

                    val response = activityApiService.getById(id)
                    val body = response.body()
                    if (!response.isSuccessful || body == null) {
                        emit(Resource.error("Unsuccessful API request", null))
                        return@flow
                    }

                    activityJsonMapper.onMap(body) { activity ->
                        val equipment = equipmentJsonMapper.mapAll(body.usedEquipment)
                        val activityWithEquipment = ActivityWithEquipment(
                            activity = activity,
                            usedEquipment = equipment
                        )

                        this.add(activityWithEquipment)
                    }
                }
            }

            emit(Resource.success(activities))
        }
    }

    suspend fun joinInById(id: Long): Result<Unit> {
        val requestBody = JoinInActivityRequestBody(
            userId = getUserId(),
            activityId = id
        )

        return object : NetworkCall<ClientActivityJson, Unit>() {
            override suspend fun makeCall(): Response<ClientActivityJson> =
                clientApiService.joinInActivity(requestBody)

            override suspend fun mapResponse(response: ClientActivityJson) = Unit

            override suspend fun saveCallResult(result: ClientActivityJson) {
                super.saveCallResult(result)
            }
        }.callForResponse()
    }

    private fun getUserId(): Long =
        userDetailsRepository.getValue(UserDetailsRepository.UserDetailsKey.USER_ID)
}