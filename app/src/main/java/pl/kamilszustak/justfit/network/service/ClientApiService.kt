package pl.kamilszustak.justfit.network.service

import pl.kamilszustak.justfit.domain.model.user.UserJson
import pl.kamilszustak.justfit.network.model.BuyProductRequestBody
import pl.kamilszustak.justfit.network.model.ClientActivityJson
import pl.kamilszustak.justfit.network.model.ClientProductJson
import pl.kamilszustak.justfit.network.model.JoinInActivityRequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ClientApiService {
    @GET("/account/client/properties/")
    suspend fun getLoggedIn(): Response<UserJson>

    @POST("/api/product/")
    suspend fun buyProduct(@Body requestBody: BuyProductRequestBody): Response<ClientProductJson>

    @GET("/api/product")
    suspend fun getAllClientProducts(): Response<List<ClientProductJson>>

    @POST("/api/activity/")
    suspend fun joinInActivity(@Body requestBody: JoinInActivityRequestBody): Response<ClientActivityJson>

    @GET("/api/activity/")
    suspend fun getAllClientActivities(): Response<List<ClientActivityJson>>
}