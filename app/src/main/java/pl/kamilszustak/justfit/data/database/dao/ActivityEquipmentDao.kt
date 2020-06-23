package pl.kamilszustak.justfit.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import pl.kamilszustak.justfit.domain.model.activity.ActivityEquipmentCrossReference

@Dao
interface ActivityEquipmentDao : BaseDao<ActivityEquipmentCrossReference> {
    @Transaction
    suspend fun replaceAllByActivityId(activityEquipment: Collection<ActivityEquipmentCrossReference>) {
        val activityId = activityEquipment.firstOrNull()?.activityId
        if (activityId != null) {
            deleteAllByActivityId(activityId)
        }

        insertAll(activityEquipment)
    }

    @Transaction
    suspend fun replaceByActivityId(activityEquipment: ActivityEquipmentCrossReference) {
        deleteAllByActivityId(activityEquipment.activityId)
        insert(activityEquipment)
    }

    @Query("DELETE FROM activity_equipment_cross_reference WHERE activity_id = :activityId")
    suspend fun deleteAllByActivityId(activityId: Long)
}