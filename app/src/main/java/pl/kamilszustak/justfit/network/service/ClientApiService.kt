package pl.kamilszustak.justfit.network.service

import pl.kamilszustak.justfit.domain.model.user.UserJson
import pl.kamilszustak.justfit.network.model.BuyProductRequestBody
import pl.kamilszustak.justfit.network.model.ClientProductJson
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ClientApiService {
    @GET("/account/client/properties/")
    suspend fun getLoggedIn(): Response<UserJson>

    @POST("/api/product/")
    suspend fun buyProduct(@Body requestBody: BuyProductRequestBody): Response<Unit>

    @GET("/api/product")
    suspend fun getAllClientProducts(userId: Long): Response<List<ClientProductJson>>
}