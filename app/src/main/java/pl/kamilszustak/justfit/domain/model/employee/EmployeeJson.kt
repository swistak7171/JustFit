package pl.kamilszustak.justfit.domain.model.employee

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import pl.kamilszustak.justfit.domain.model.JsonModel

@JsonClass(generateAdapter = true)
data class EmployeeJson(
    @Json(name = "firstName")
    var name: String,

    @Json(name = "surName")
    var surname: String,

    @Json(name = "email")
    var email: String,

    @Json(name = "phoneNumber")
    var phoneNumber: String,

    @Json(name = "specialization")
    var specialization: String,

    @Json(name = "workingHours")
    var workHours: String
) : JsonModel()