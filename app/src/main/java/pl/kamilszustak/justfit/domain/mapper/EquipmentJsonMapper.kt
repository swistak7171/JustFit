package pl.kamilszustak.justfit.domain.mapper

import pl.kamilszustak.justfit.domain.model.equipment.Equipment
import pl.kamilszustak.justfit.domain.model.equipment.EquipmentJson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EquipmentJsonMapper @Inject constructor() : JsonModelMapper<EquipmentJson, Equipment>() {
    override fun map(model: EquipmentJson): Equipment = Equipment(
        name = model.name,
        specification = model.specification,
        isAvailable = model.isAvailable
    ).apply {
        this.id = model.id
    }
}