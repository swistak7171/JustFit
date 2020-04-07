package pl.kamilszustak.justfit.domain.model.event

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import pl.kamilszustak.justfit.common.moshi.annotation.LocalDateTimeField
import pl.kamilszustak.justfit.domain.model.JsonModel
import java.util.Date

@JsonClass(generateAdapter = true)
data class EventJson(
    @Json(name = "workerId")
    var employeeId: Long,

    @Json(name = "clientIds")
    var clientsIds: List<Long>,

    @Json(name = "eventName")
    var name: String,

    @Json(name = "eventStartDate")
    @LocalDateTimeField
    var startDate: Date,

    @Json(name = "eventEndDate")
    @LocalDateTimeField
    var endDate: Date
) : JsonModel()