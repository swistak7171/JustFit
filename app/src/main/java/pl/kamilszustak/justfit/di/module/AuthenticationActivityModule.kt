package pl.kamilszustak.justfit.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.kamilszustak.justfit.di.scope.FragmentScope
import pl.kamilszustak.justfit.ui.authentication.login.LoginFragment
import pl.kamilszustak.justfit.ui.authentication.overview.OverviewFragment
import pl.kamilszustak.justfit.ui.authentication.overview.photo.OverviewPhotoFragment

@Module
abstract class AuthenticationActivityModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeOverviewFragment(): OverviewFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeOverviewPhotoFragment(): OverviewPhotoFragment
}