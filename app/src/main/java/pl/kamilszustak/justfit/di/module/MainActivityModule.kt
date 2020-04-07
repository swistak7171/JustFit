package pl.kamilszustak.justfit.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.kamilszustak.justfit.di.scope.FragmentScope
import pl.kamilszustak.justfit.ui.main.employee.EmployeesFragment
import pl.kamilszustak.justfit.ui.main.employee.details.EmployeeDetailsFragment
import pl.kamilszustak.justfit.ui.main.equipment.EquipmentFragment
import pl.kamilszustak.justfit.ui.main.profile.ProfileFragment
import pl.kamilszustak.justfit.ui.main.schedule.ScheduleFragment

@Module
abstract class MainActivityModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeScheduleFragment(): ScheduleFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeEquipmentFragment(): EquipmentFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeEmployeesFragment(): EmployeesFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeEmployeeDetailsFragment(): EmployeeDetailsFragment
}