package pl.kamilszustak.justfit.domain.model.activity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import pl.kamilszustak.justfit.domain.model.equipment.Equipment

data class ActivityWithEquipment(
    @Embedded
    val activity: ActivityEntity,

    @Relation(
        entity = Equipment::class,
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = ActivityEquipmentCrossReference::class,
            parentColumn = "activity_id",
            entityColumn = "equipment_id"
        )
    )
    val usedEquipment: List<Equipment>
)