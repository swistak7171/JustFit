package pl.kamilszustak.justfit.data.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.justfit.common.data.NetworkBoundResource
import pl.kamilszustak.justfit.common.data.Resource
import pl.kamilszustak.justfit.data.database.dao.ActivityDao
import pl.kamilszustak.justfit.data.database.dao.EquipmentDao
import pl.kamilszustak.justfit.domain.mapper.EquipmentJsonMapper
import pl.kamilszustak.justfit.domain.mapper.activity.ActivityJsonMapper
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
                activityJsonMapper.onMapAll(result) { activities ->
                    activityDao.replaceAll(activities)
                }

                val equipment = result.map(ActivityJson::usedEquipment)
                    .flatten()

                equipmentJsonMapper.onMapAll(equipment) { entities ->
                    equipmentDao.insertAll(entities)
                }
            }
        }.asFlow()
    }
}