package pl.kamilszustak.justfit.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.kamilszustak.justfit.di.scope.ActivityScope
import pl.kamilszustak.justfit.ui.authentication.AuthenticationActivity
import pl.kamilszustak.justfit.ui.main.MainActivity

@Module
abstract class ActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            AuthenticationActivityModule::class
        ]
    )
    abstract fun contributeAuthenticationActivity(): AuthenticationActivity

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            MainActivityModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity
}