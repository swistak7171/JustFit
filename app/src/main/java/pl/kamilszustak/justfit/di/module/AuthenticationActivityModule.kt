package pl.kamilszustak.justfit.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.kamilszustak.justfit.di.scope.FragmentScope
import pl.kamilszustak.justfit.ui.authentication.login.LoginFragment

@Module
abstract class AuthenticationActivityModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment
}