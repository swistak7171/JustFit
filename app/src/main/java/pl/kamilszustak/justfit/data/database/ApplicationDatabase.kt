package pl.kamilszustak.justfit.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pl.kamilszustak.justfit.R

@Database(
    entities = [
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class ApplicationDatabase : RoomDatabase() {

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