package pl.kamilszustak.justfit.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.justfit.domain.model.user.User

@Dao
interface UserDao : BaseDao<User> {
    @Query("DELETE FROM users")
    suspend fun deleteAll()

    @Query("SELECT * FROM users WHERE id = :id")
    fun getById(id: Long): Flow<User>
}