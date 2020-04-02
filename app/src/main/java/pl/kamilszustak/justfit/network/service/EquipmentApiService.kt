package pl.kamilszustak.justfit.network.service

import pl.kamilszustak.justfit.domain.model.equipment.EquipmentJson
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EquipmentApiService {
    @GET("/equipment/getAll")
    suspend fun getAll(): Response<List<EquipmentJson>>

    @GET("/equipment/getAllAvailable")
    suspend fun getAllAvailable(): Response<List<EquipmentJson>>

    @GET("/equipment/changeAvailability")
    suspend fun changeAvailabilityById(@Query("id") id: Long, @Query("available") isAvailable: Boolean): Response<Unit>
}