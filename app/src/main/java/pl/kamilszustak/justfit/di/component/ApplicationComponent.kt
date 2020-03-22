package pl.kamilszustak.justfit.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import pl.kamilszustak.justfit.application.BaseApplication
import pl.kamilszustak.justfit.di.module.ActivityModule
import pl.kamilszustak.justfit.di.module.ApplicationModule
import pl.kamilszustak.justfit.di.module.DatabaseModule
import pl.kamilszustak.justfit.di.module.NetworkModule
import pl.kamilszustak.justfit.di.module.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        DatabaseModule::class,
        NetworkModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}