package pl.kamilszustak.justfit.domain.model.activity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "activity_equipment_cross_reference",
    primaryKeys = [
        "activity_id",
        "equipment_id"
    ]
)
data class ActivityEquipmentCrossReference(
    @ColumnInfo(name = "activity_id")
    val activityId: Long,

    @ColumnInfo(name = "equipment_id")
    val equipmentId: Long
)