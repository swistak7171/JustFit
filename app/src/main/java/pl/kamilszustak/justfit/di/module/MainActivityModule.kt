package pl.kamilszustak.justfit.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.kamilszustak.justfit.di.scope.FragmentScope

@Module
abstract class MainActivityModule {
    // @FragmentScope
    // @ContributesAndroidInjector
    // abstract fun contributeProfileFragment(): ProfileFragment
}