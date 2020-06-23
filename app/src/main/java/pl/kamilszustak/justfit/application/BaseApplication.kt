package pl.kamilszustak.justfit.application

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import leakcanary.AppWatcher
import pl.kamilszustak.justfit.di.component.ApplicationComponent
import pl.kamilszustak.justfit.di.component.DaggerApplicationComponent
import timber.log.Timber

class BaseApplication : DaggerApplication() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        initializeAppWatcher()
        initializeTimber()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        applicationComponent

    private fun initializeAppWatcher() {
        AppWatcher.config = AppWatcher.config.copy(watchFragmentViews = true)
    }

    private fun initializeTimber() {
        Timber.plant(Timber.DebugTree())
    }
}