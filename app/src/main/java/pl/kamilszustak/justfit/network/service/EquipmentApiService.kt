package pl.kamilszustak.justfit.network.service

import pl.kamilszustak.justfit.domain.model.equipment.EquipmentJson
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EquipmentApiService {
    @GET("/justfit/equipment/getAll")
    suspend fun getAll(): Response<List<EquipmentJson>>

    @GET("/justfit/equipment/getAllAvailable")
    suspend fun getAllAvailable(): Response<List<EquipmentJson>>

    @GET("/justfit/equipment/changeAvailability")
    suspend fun updateAvailabilityById(@Query("id") id: Long, @Query("available") isAvailable: Boolean): Response<Unit>
}