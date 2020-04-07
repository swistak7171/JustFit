package pl.kamilszustak.justfit.network.service

import pl.kamilszustak.justfit.domain.model.event.EventJson
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EventApiService {
    @GET("/getAllEvents")
    suspend fun getAll(): Response<List<EventJson>>

    @GET("/getEvent/{id}")
    suspend fun getById(@Path("id") id: Long): Response<EventJson>
}