package pl.kamilszustak.justfit.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import pl.kamilszustak.justfit.data.database.ApplicationDatabase
import pl.kamilszustak.justfit.data.database.dao.ActivityDao
import pl.kamilszustak.justfit.data.database.dao.ClientProductDao
import pl.kamilszustak.justfit.data.database.dao.EmployeeDao
import pl.kamilszustak.justfit.data.database.dao.EquipmentDao
import pl.kamilszustak.justfit.data.database.dao.EventDao
import pl.kamilszustak.justfit.data.database.dao.ProductDao
import pl.kamilszustak.justfit.data.database.dao.UserDao
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(application: Application): ApplicationDatabase =
        ApplicationDatabase(application)

    @Provides
    @Singleton
    fun provideUserDao(applicationDatabase: ApplicationDatabase): UserDao =
        applicationDatabase.getUserDao()

    @Provides
    @Singleton
    fun provideEquipmentDao(applicationDatabase: ApplicationDatabase): EquipmentDao =
        applicationDatabase.getEquipmentDao()

    @Provides
    @Singleton
    fun provideEmployeeDao(applicationDatabase: ApplicationDatabase): EmployeeDao =
        applicationDatabase.getEmployeeDao()

    @Provides
    @Singleton
    fun provideEventDao(applicationDatabase: ApplicationDatabase): EventDao =
        applicationDatabase.getEventDao()

    @Provides
    @Singleton
    fun provideProductDao(applicationDatabase: ApplicationDatabase): ProductDao =
        applicationDatabase.getProductDao()

    @Provides
    @Singleton
    fun provideClientProductDao(applicationDatabase: ApplicationDatabase): ClientProductDao =
        applicationDatabase.getClientProductDao()

    @Provides
    @Singleton
    fun provideActivityDao(applicationDatabase: ApplicationDatabase): ActivityDao =
        applicationDatabase.getActivityDao()
}