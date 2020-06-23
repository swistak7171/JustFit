package pl.kamilszustak.justfit.domain.model.product

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import pl.kamilszustak.justfit.domain.model.json.JsonModel

@JsonClass(generateAdapter = true)
data class ProductJson(
    @Json(name = "name")
    val name: String,

    @Json(name = "description")
    val description: String,

    @Json(name = "price")
    val price: Double,

    @Json(name = "active")
    val isActive: Boolean
) : JsonModel()