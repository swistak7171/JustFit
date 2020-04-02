package pl.kamilszustak.justfit.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.justfit.domain.model.equipment.Equipment

@Dao
interface EquipmentDao : BaseDao<Equipment> {
    @Query("SELECT * FROM equipment")
    fun getAll(): Flow<Equipment>

    @Query("SELECT * FROM equipment WHERE is_available = 1")
    fun getAllAvailable(): Flow<Equipment>

    @Query("UPDATE equipment SET is_available = :isAvailable WHERE id = :id")
    suspend fun changeAvailabilityById(id: Long, isAvailable: Boolean)
}