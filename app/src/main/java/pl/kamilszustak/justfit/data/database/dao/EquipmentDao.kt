package pl.kamilszustak.justfit.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.justfit.domain.model.equipment.Equipment

@Dao
interface EquipmentDao : BaseDao<Equipment> {
    @Query("SELECT * FROM equipment")
    fun getAll(): Flow<List<Equipment>>

    @Query("SELECT * FROM equipment WHERE is_available = 1")
    fun getAllAvailable(): Flow<List<Equipment>>

    @Query("UPDATE equipment SET is_available = :isAvailable WHERE id = :id")
    suspend fun changeAvailabilityById(id: Long, isAvailable: Boolean)

    @Transaction
    suspend fun replaceAll(equipment: Collection<Equipment>) {
        deleteAll()
        insertAll(equipment)
    }

    @Transaction
    suspend fun replaceAllAvailable(equipment: Collection<Equipment>) {
        deleteAllAvailable()
        insertAll(equipment)
    }

    @Query("DELETE FROM equipment")
    suspend fun deleteAll()

    @Query("DELETE FROM equipment WHERE is_available = 1")
    suspend fun deleteAllAvailable()
}