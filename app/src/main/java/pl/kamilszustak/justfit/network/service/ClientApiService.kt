package pl.kamilszustak.justfit.network.service

import pl.kamilszustak.justfit.domain.model.user.UserJson
import pl.kamilszustak.justfit.network.model.BuyProductRequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ClientApiService {
    @GET("/account/client/properties/")
    suspend fun getLoggedIn(): Response<UserJson>

    @POST("/api/product")
    suspend fun buyProduct(@Body requestBody: BuyProductRequestBody): Response<Unit>
}