package pl.kamilszustak.justfit.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.data.database.dao.EmployeeDao
import pl.kamilszustak.justfit.data.database.dao.EquipmentDao
import pl.kamilszustak.justfit.data.database.dao.UserDao
import pl.kamilszustak.justfit.domain.model.employee.Employee
import pl.kamilszustak.justfit.domain.model.equipment.Equipment
import pl.kamilszustak.justfit.domain.model.user.User

@Database(
    entities = [
        User::class,
        Equipment::class,
        Employee::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao
    abstract fun getEquipmentDao(): EquipmentDao
    abstract fun getEmployeeDao(): EmployeeDao

    companion object {
        private var INSTANCE: ApplicationDatabase? = null

        operator fun invoke(application: Application): ApplicationDatabase {
            return INSTANCE ?: synchronized(ApplicationDatabase::class) {
                build(application).also {
                    INSTANCE = it
                }
            }
        }

        private fun build(application: Application): ApplicationDatabase {
            return Room.databaseBuilder(
                application.applicationContext,
                ApplicationDatabase::class.java,
                application.applicationContext.getString(R.string.database_name)
            )
                .build()
        }
    }
}