package pl.kamilszustak.justfit.network.service

import pl.kamilszustak.justfit.domain.model.event.EventJson
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface EventApiService {
    @GET("/events/getAll")
    suspend fun getAll(): Response<List<EventJson>>

    @GET("/events/get/{id}")
    suspend fun getById(@Path("id") id: Long): Response<EventJson>

    @PUT("/events/join/{clientId}")
    @FormUrlEncoded
    suspend fun join(@Path("clientId") clientId: Long, @Field("id") eventId: Long): Response<EventJson>
}