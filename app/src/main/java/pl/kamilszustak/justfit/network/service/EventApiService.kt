package pl.kamilszustak.justfit.network.service

import pl.kamilszustak.justfit.domain.model.event.EventJson
import pl.kamilszustak.justfit.network.model.JoinLeaveEventRequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface EventApiService {
    @GET("/events/getAll")
    suspend fun getAll(): Response<List<EventJson>>

    @GET("/events/get/{id}")
    suspend fun getById(@Path("id") id: Long): Response<EventJson>

    @PUT("/events/join/{clientId}")
    suspend fun join(@Path("clientId") clientId: Long, @Body requestBody: JoinLeaveEventRequestBody): Response<EventJson>

    @PUT("/events/leave/{clientId}")
    suspend fun leave(@Path("clientId") clientId: Long, @Body requestBody: JoinLeaveEventRequestBody): Response<EventJson>
}