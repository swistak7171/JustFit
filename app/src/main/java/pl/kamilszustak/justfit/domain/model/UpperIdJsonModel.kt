package pl.kamilszustak.justfit.domain.model

import com.squareup.moshi.Json

abstract class UpperIdJsonModel {
    @Json(name = "ID")
    var id: Long = 0
}