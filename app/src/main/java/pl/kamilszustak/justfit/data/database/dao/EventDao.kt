package pl.kamilszustak.justfit.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.justfit.domain.model.event.Event

@Dao
interface EventDao : BaseDao<Event> {
    @Query("SELECT * FROM events")
    fun getAll(): Flow<List<Event>>

    @Query("SELECT * FROM events WHERE id = :id")
    fun getById(id: Long): Flow<Event>

    @Transaction
    suspend fun replaceAll(events: Collection<Event>) {
        deleteAll()
        insertAll(events)
    }

    @Query("DELETE FROM events")
    suspend fun deleteAll()

    @Query("DELETE FROM events WHERE id = :id")
    suspend fun deleteById(id: Long)
}