package pl.kamilszustak.justfit.network.service

import pl.kamilszustak.justfit.domain.model.employee.EmployeeJson
import retrofit2.Response
import retrofit2.http.GET

interface EmployeeApiService {
    @GET("/workers")
    suspend fun getAllEmployees(): Response<List<EmployeeJson>>

    @GET("/getWorkerById/{id}")
    suspend fun getEmployeeById(id: Long): Response<EmployeeJson>
}