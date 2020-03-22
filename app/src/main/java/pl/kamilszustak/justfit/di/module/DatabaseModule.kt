package pl.kamilszustak.justfit.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import pl.kamilszustak.justfit.data.database.ApplicationDatabase
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(application: Application): ApplicationDatabase =
        ApplicationDatabase(application)

    // @Provides
    // @Singleton
    // fun provideUserDao(applicationDatabase: ApplicationDatabase): UserDao =
    //     applicationDatabase.getUserDao()
}