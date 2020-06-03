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
import pl.kamilszustak.justfit.ui.main.employee.details.EmployeeDetailsViewModel
import pl.kamilszustak.justfit.ui.main.equipment.EquipmentViewModel
import pl.kamilszustak.justfit.ui.main.event.EventsViewModel
import pl.kamilszustak.justfit.ui.main.product.ProductsViewModel
import pl.kamilszustak.justfit.ui.main.product.client.ClientProductsViewModel
import pl.kamilszustak.justfit.ui.main.profile.ProfileViewModel

@Module
interface ViewModelModule {
    @Binds
    fun bindAndroidViewModelFactory(factory: AndroidViewModelFactory): ViewModelProvider.AndroidViewModelFactory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun bindProfileViewModel(profileViewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EquipmentViewModel::class)
    fun bindEquipmentViewModel(equipmentViewModel: EquipmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EmployeesViewModel::class)
    fun bindEmployeesViewModel(employeesViewModel: EmployeesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EmployeeDetailsViewModel::class)
    fun bindEmployeeDetailsViewModel(employeeDetailsViewModel: EmployeeDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EventsViewModel::class)
    fun bindEventsViewModel(eventsViewModel: EventsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductsViewModel::class)
    fun bindProductsViewModel(productsViewModel: ProductsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ClientProductsViewModel::class)
    fun bindClientProductsViewModel(clientProductsViewModel: ClientProductsViewModel): ViewModel
}