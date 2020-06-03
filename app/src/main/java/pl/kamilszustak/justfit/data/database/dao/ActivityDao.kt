package pl.kamilszustak.justfit.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.justfit.domain.model.activity.ActivityEntity
import pl.kamilszustak.justfit.domain.model.activity.ActivityWithEquipment

@Dao
interface ActivityDao : BaseDao<ActivityEntity> {
    @Query("SELECT * FROM activities")
    @Transaction
    fun getAll(): Flow<List<ActivityWithEquipment>>

    @Transaction
    suspend fun replaceAll(activities: Collection<ActivityEntity>) {
        deleteAll()
        insertAll(activities)
    }

    @Query("DELETE FROM activities")
    suspend fun deleteAll()
}