package pl.kamilszustak.justfit.di.module

import dagger.Binds
import dagger.Module
import pl.kamilszustak.justfit.domain.usecase.database.ClearDatabaseUseCase
import pl.kamilszustak.justfit.domain.usecase.database.ClearDatabaseUseCaseImpl
import pl.kamilszustak.justfit.domain.usecase.employee.GetAllEmployeesUseCase
import pl.kamilszustak.justfit.domain.usecase.employee.GetAllEmployeesUseCaseImpl
import pl.kamilszustak.justfit.domain.usecase.employee.GetEmployeeByIdUseCase
import pl.kamilszustak.justfit.domain.usecase.employee.GetEmployeeByIdUseCaseImpl
import pl.kamilszustak.justfit.domain.usecase.equipment.GetAllAvailableEquipmentUseCase
import pl.kamilszustak.justfit.domain.usecase.equipment.GetAllAvailableEquipmentUseCaseImpl
import pl.kamilszustak.justfit.domain.usecase.equipment.GetAllEquipmentUseCase
import pl.kamilszustak.justfit.domain.usecase.equipment.GetAllEquipmentUseCaseImpl
import pl.kamilszustak.justfit.domain.usecase.equipment.SetEquipmentAsUnavailableUseCase
import pl.kamilszustak.justfit.domain.usecase.equipment.SetEquipmentAsUnavailableUseCaseImpl
import pl.kamilszustak.justfit.domain.usecase.event.GetAllEventsUseCase
import pl.kamilszustak.justfit.domain.usecase.event.GetAllEventsUseCaseImpl
import pl.kamilszustak.justfit.domain.usecase.event.GetEventByIdUseCase
import pl.kamilszustak.justfit.domain.usecase.event.GetEventByIdUseCaseImpl
import pl.kamilszustak.justfit.domain.usecase.user.GetUserUseCase
import pl.kamilszustak.justfit.domain.usecase.user.GetUserUseCaseImpl
import pl.kamilszustak.justfit.domain.usecase.user.IsUserAuthenticated
import pl.kamilszustak.justfit.domain.usecase.user.IsUserAuthenticatedImpl
import pl.kamilszustak.justfit.domain.usecase.user.LogoutUserUseCase
import pl.kamilszustak.justfit.domain.usecase.user.LogoutUserUseCaseImpl

@Module
abstract class UseCaseModule {
    @Binds
    abstract fun bindGetUserUseCase(useCaseImpl: GetUserUseCaseImpl): GetUserUseCase

    @Binds
    abstract fun bindClearDatabaseUserCase(useCaseImpl: ClearDatabaseUseCaseImpl): ClearDatabaseUseCase

    @Binds
    abstract fun bindIsUserAuthenticated(useCaseImpl: IsUserAuthenticatedImpl): IsUserAuthenticated

    @Binds
    abstract fun bindLogoutUserUseCase(useCaseImpl: LogoutUserUseCaseImpl): LogoutUserUseCase

    @Binds
    abstract fun bindGetAllEquipmentUseCase(useCaseImpl: GetAllEquipmentUseCaseImpl): GetAllEquipmentUseCase

    @Binds
    abstract fun bindGetAllAvailableEquipmentUseCase(useCaseImpl: GetAllAvailableEquipmentUseCaseImpl): GetAllAvailableEquipmentUseCase

    @Binds
    abstract fun bindSetEquipmentAsUnavailableUseCase(useCaseImpl: SetEquipmentAsUnavailableUseCaseImpl): SetEquipmentAsUnavailableUseCase

    @Binds
    abstract fun bindGetAllEmployeesUseCase(useCaseImpl: GetAllEmployeesUseCaseImpl): GetAllEmployeesUseCase

    @Binds
    abstract fun bindGetEmployeeByIdUseCase(useCaseImpl: GetEmployeeByIdUseCaseImpl): GetEmployeeByIdUseCase

    @Binds
    abstract fun bindGetAllEventsUseCase(useCaseImpl: GetAllEventsUseCaseImpl): GetAllEventsUseCase

    @Binds
    abstract fun bindGetEventByIdUseCase(useCaseImpl: GetEventByIdUseCaseImpl): GetEventByIdUseCase
}