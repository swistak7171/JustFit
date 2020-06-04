package pl.kamilszustak.justfit.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JoinInActivityRequestBody(
    @Json(name = "user")
    val userId: Long,

    @Json(name = "id_exercise")
    val activityId: Long,

    @Json(name = "active")
    val active: Boolean = true
)