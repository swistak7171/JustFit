package pl.kamilszustak.justfit.domain.model

import com.squareup.moshi.Json

abstract class JsonModel {
    @Json(name = "id")
    var id: Long = 0
}