package pl.kamilszustak.justfit.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.justfit.domain.model.employee.Employee

@Dao
interface EmployeeDao : BaseDao<Employee> {
    @Query("SELECT * FROM employees")
    fun getAll(): Flow<List<Employee>>

    @Query("SELECT * FROM employees WHERE id = :id")
    fun getById(id: Long): Flow<Employee>

    @Transaction
    suspend fun replaceAll(employees: Collection<Employee>) {
        deleteAll()
        insertAll(employees)
    }

    @Query("DELETE FROM employees")
    suspend fun deleteAll()
}