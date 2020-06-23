package pl.kamilszustak.justfit.domain.model.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import pl.kamilszustak.justfit.domain.model.json.JsonModel

@JsonClass(generateAdapter = true)
data class UserJson(
    @Json(name = "email")
    var email: String,

    @Json(name = "username")
    var username: String,

    @Json(name = "first_name")
    var name: String,

    @Json(name = "last_name")
    var surname: String,

    @Json(name = "phone_number")
    var phoneNumber: String
) : JsonModel()