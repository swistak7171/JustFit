package pl.kamilszustak.justfit.domain.model.equipment

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import pl.kamilszustak.justfit.domain.model.UpperIdJsonModel

@JsonClass(generateAdapter = true)
data class EquipmentJson(
    @Json(name = "equipmentName")
    var name: String,

    @Json(name = "specification")
    var specification: String,

    @Json(name = "availability")
    var isAvailable: Boolean
) : UpperIdJsonModel()