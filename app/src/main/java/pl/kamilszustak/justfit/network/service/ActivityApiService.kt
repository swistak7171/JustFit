package pl.kamilszustak.justfit.network.service

import pl.kamilszustak.justfit.domain.model.activity.ActivityJson
import retrofit2.Response
import retrofit2.http.GET

interface ActivityApiService {
    @GET("/getAllActivities")
    suspend fun getAll(): Response<List<ActivityJson>>
}