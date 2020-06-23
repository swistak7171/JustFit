package pl.kamilszustak.justfit.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.kamilszustak.justfit.di.scope.FragmentScope
import pl.kamilszustak.justfit.ui.main.activity.ActivitiesFragment
import pl.kamilszustak.justfit.ui.main.activity.client.ClientActivitiesFragment
import pl.kamilszustak.justfit.ui.main.activity.details.ActivityDetailsFragment
import pl.kamilszustak.justfit.ui.main.employee.EmployeesFragment
import pl.kamilszustak.justfit.ui.main.employee.details.EmployeeDetailsFragment
import pl.kamilszustak.justfit.ui.main.equipment.EquipmentFragment
import pl.kamilszustak.justfit.ui.main.event.EventsFragment
import pl.kamilszustak.justfit.ui.main.event.details.EventDetailsFragment
import pl.kamilszustak.justfit.ui.main.product.ProductsFragment
import pl.kamilszustak.justfit.ui.main.product.client.ClientProductsFragment
import pl.kamilszustak.justfit.ui.main.profile.ProfileFragment
import pl.kamilszustak.justfit.ui.main.schedule.ScheduleFragment

@Module
interface MainActivityModule {
    @FragmentScope
    @ContributesAndroidInjector
    fun contributeProfileFragment(): ProfileFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun contributeScheduleFragment(): ScheduleFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun contributeEquipmentFragment(): EquipmentFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun contributeEmployeesFragment(): EmployeesFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun contributeEmployeeDetailsFragment(): EmployeeDetailsFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun contributeEventsFragment(): EventsFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun contributeProductsFragment(): ProductsFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun contributeClientProductsFragment(): ClientProductsFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun contributeActivitiesFragment(): ActivitiesFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun contributeActivityDetailsFragment(): ActivityDetailsFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun contributeClientActivitiesFragment(): ClientActivitiesFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun contributeEventDetailsFragment(): EventDetailsFragment
}