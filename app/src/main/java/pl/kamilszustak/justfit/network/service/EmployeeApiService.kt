package pl.kamilszustak.justfit.network.service

import pl.kamilszustak.justfit.domain.model.employee.EmployeeJson
import retrofit2.Response
import retrofit2.http.GET

interface EmployeeApiService {
    @GET("/getWorkersList")
    suspend fun getAll(): Response<List<EmployeeJson>>

    @GET("/getWorkerById/{id}")
    suspend fun getById(id: Long): Response<EmployeeJson>
}