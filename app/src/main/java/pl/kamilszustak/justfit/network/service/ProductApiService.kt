package pl.kamilszustak.justfit.network.service

import pl.kamilszustak.justfit.domain.model.product.ProductJson
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApiService {
    @GET("/products")
    suspend fun getAll(): Response<List<ProductJson>>

    @GET("/products/{id}")
    suspend fun getById(@Path("id") id: Long): Response<ProductJson>
}