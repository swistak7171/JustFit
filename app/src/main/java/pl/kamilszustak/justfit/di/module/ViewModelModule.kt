package pl.kamilszustak.justfit.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pl.kamilszustak.justfit.common.AndroidViewModelFactory
import pl.kamilszustak.justfit.di.ViewModelKey
import pl.kamilszustak.justfit.ui.authentication.login.LoginViewModel
import pl.kamilszustak.justfit.ui.main.employee.EmployeesViewModel
import pl.kamilszustak.justfit.ui.main.equipment.EquipmentViewModel
import pl.kamilszustak.justfit.ui.main.profile.ProfileViewModel

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindAndroidViewModelFactory(factory: AndroidViewModelFactory): ViewModelProvider.AndroidViewModelFactory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EquipmentViewModel::class)
    abstract fun bindEquipmentViewModel(equipmentViewModel: EquipmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EmployeesViewModel::class)
    abstract fun bindEmployeesViewModel(employeesViewModel: EmployeesViewModel): ViewModel
}