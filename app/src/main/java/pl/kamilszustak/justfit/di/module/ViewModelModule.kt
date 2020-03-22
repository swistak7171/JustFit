package pl.kamilszustak.justfit.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pl.kamilszustak.justfit.di.ViewModelKey

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindAndroidViewModelFactory(factory: ViewModelProvider.AndroidViewModelFactory): ViewModelProvider.AndroidViewModelFactory

    // @Binds
    // @IntoMap
    // @ViewModelKey(LoginViewModel::class)
    // abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel
}