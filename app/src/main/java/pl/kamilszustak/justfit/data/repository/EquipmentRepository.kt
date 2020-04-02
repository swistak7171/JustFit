package pl.kamilszustak.justfit.data.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.justfit.common.data.NetworkBoundResource
import pl.kamilszustak.justfit.common.data.NetworkCall
import pl.kamilszustak.justfit.common.data.Resource
import pl.kamilszustak.justfit.data.database.dao.EquipmentDao
import pl.kamilszustak.justfit.domain.mapper.EquipmentJsonMapper
import pl.kamilszustak.justfit.domain.model.equipment.Equipment
import pl.kamilszustak.justfit.domain.model.equipment.EquipmentJson
import pl.kamilszustak.justfit.network.service.EquipmentApiService
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EquipmentRepository @Inject constructor(
    private val equipmentDao: EquipmentDao,
    private val equipmentApiService: EquipmentApiService,
    private val equipmentJsonMapper: EquipmentJsonMapper
) {
    fun getAll(shouldFetch: Boolean = true): Flow<Resource<List<Equipment>>> {
        return object : NetworkBoundResource<List<EquipmentJson>, List<Equipment>>() {
            override fun loadFromDatabase(): Flow<List<Equipment>> =
                equipmentDao.getAll()

            override fun shouldFetch(data: List<Equipment>?): Boolean = shouldFetch

            override suspend fun fetchFromNetwork(): Response<List<EquipmentJson>> =
                equipmentApiService.getAll()

            override suspend fun saveFetchResult(result: List<EquipmentJson>) {
                equipmentJsonMapper.onMapAll(result) { equipment ->
                    equipmentDao.replaceAll(equipment)
                }
            }
        }.asFlow()
    }

    fun getAllAvailable(shouldFetch: Boolean = true): Flow<Resource<List<Equipment>>> {
        return object : NetworkBoundResource<List<EquipmentJson>, List<Equipment>>() {
            override fun loadFromDatabase(): Flow<List<Equipment>> =
                equipmentDao.getAllAvailable()

            override fun shouldFetch(data: List<Equipment>?): Boolean = shouldFetch

            override suspend fun fetchFromNetwork(): Response<List<EquipmentJson>> =
                equipmentApiService.getAllAvailable()

            override suspend fun saveFetchResult(result: List<EquipmentJson>) {
                equipmentJsonMapper.onMapAll(result) { equipment ->
                    equipmentDao.replaceAllAvailable(equipment)
                }
            }
        }.asFlow()
    }

    suspend fun updateAvailabilityById(id: Long, isAvailable: Boolean): Result<Unit> {
        return object : NetworkCall<Unit, Unit>() {
            override suspend fun makeCall(): Response<Unit> =
                equipmentApiService.updateAvailabilityById(id, isAvailable)

            override suspend fun mapResponse(response: Unit): Unit = response

            override suspend fun onResponseSuccess() {
                equipmentDao.updateAvailabilityById(id, isAvailable)
            }
        }.call()
    }
}