package pl.kamilszustak.justfit.data.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.justfit.common.data.NetworkBoundResource
import pl.kamilszustak.justfit.common.data.Resource
import pl.kamilszustak.justfit.data.database.dao.ActivityDao
import pl.kamilszustak.justfit.data.database.dao.ActivityEquipmentDao
import pl.kamilszustak.justfit.data.database.dao.EquipmentDao
import pl.kamilszustak.justfit.domain.mapper.EquipmentJsonMapper
import pl.kamilszustak.justfit.domain.mapper.activity.ActivityJsonMapper
import pl.kamilszustak.justfit.domain.model.activity.ActivityEquipmentCrossReference
import pl.kamilszustak.justfit.domain.model.activity.ActivityJson
import pl.kamilszustak.justfit.domain.model.activity.ActivityWithEquipment
import pl.kamilszustak.justfit.network.service.ActivityApiService
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActivityRepository @Inject constructor(
    private val activityDao: ActivityDao,
    private val equipmentDao: EquipmentDao,
    private val activityEquipmentDao: ActivityEquipmentDao,
    private val activityApiService: ActivityApiService,
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

                    activity.usedEquipment.forEach { equipment ->
                        val activityEquipment = ActivityEquipmentCrossReference(
                            activityId = activity.id,
                            equipmentId = equipment.id
                        )

                        activityEquipmentDao.replaceByActivityId(activityEquipment)
                    }
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
                activityJsonMapper.onMap(result) { activity ->
                    activityDao.insert(activity)
                }

                equipmentJsonMapper.onMapAll(result.usedEquipment) { equipment ->
                    equipmentDao.insertAll(equipment)
                }
            }
        }.asFlow()
    }
}