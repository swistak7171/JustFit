package pl.kamilszustak.justfit.network.service

import pl.kamilszustak.justfit.domain.model.activity.ActivityJson
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ActivityApiService {
    @GET("/getAllActivities")
    suspend fun getAll(): Response<List<ActivityJson>>

    @GET("/getActivitiesById/{id}")
    suspend fun getById(@Path("id") id: Long): Response<ActivityJson>

    @GET("/getActivitiesByDate/{date}")
    suspend fun getAllByDate(@Path("date") date: String): Response<List<ActivityJson>>
}