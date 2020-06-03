package pl.kamilszustak.justfit.domain.model.activity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import pl.kamilszustak.justfit.domain.model.equipment.Equipment

data class ActivityWithEquipment(
    @Embedded
    val activity: ActivityEntity,

    @Relation(
        parentColumn = "activity_id",
        entityColumn = "equipment_id",
        associateBy = Junction(ActivityEquipmentCrossReference::class)
    )
    val usedEquipment: List<Equipment>
)