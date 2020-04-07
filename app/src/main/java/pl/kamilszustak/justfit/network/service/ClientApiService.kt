package pl.kamilszustak.justfit.network.service

import pl.kamilszustak.justfit.domain.model.user.UserJson
import retrofit2.Response
import retrofit2.http.GET

interface ClientApiService {
    @GET("/account/client/properties/")
    suspend fun getLoggedIn(): Response<UserJson>
}