package pl.kamilszustak.justfit.domain.model.activity

import androidx.room.Entity

@Entity(
    tableName = "activity_equipment_cross_reference",
    primaryKeys = [
        "activity_id",
        "equipment_id"
    ]
)
data class ActivityEquipmentCrossReference(
    val activityId: Long,
    val equipmentId: Long
)