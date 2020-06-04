package pl.kamilszustak.justfit.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import pl.kamilszustak.justfit.domain.model.json.JsonModel

@JsonClass(generateAdapter = true)
data class ClientProductJson(
    @Json(name = "user")
    val userId: Long,

    @Json(name = "id_product")
    val productId: Long,

    @Json(name = "active")
    val active: Boolean
) : JsonModel()