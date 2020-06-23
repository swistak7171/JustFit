package pl.kamilszustak.justfit.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JoinLeaveEventRequestBody(
    @Json(name = "id")
    val eventId: Long
)