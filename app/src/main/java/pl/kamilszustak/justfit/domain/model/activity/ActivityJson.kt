package pl.kamilszustak.justfit.domain.model.activity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import pl.kamilszustak.justfit.domain.model.json.JsonModel
import pl.kamilszustak.justfit.domain.model.equipment.EquipmentJson

@JsonClass(generateAdapter = true)
data class ActivityJson(
    @Json(name = "name")
    val name: String,

    @Json(name = "leader")
    val leaderName: String,

    @Json(name = "activityType")
    val type: String,

    @Json(name = "date")
    val date: String,

    @Json(name = "hourStart")
    val startTime: String,

    @Json(name = "hourEnd")
    val endTime: String,

    @Json(name = "equipmentUsed")
    val usedEquipment: List<EquipmentJson>,

    @Json(name = "classCanceled")
    val isCancelled: Boolean
) : JsonModel()