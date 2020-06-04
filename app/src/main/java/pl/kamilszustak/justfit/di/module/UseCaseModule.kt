package pl.kamilszustak.justfit.di.module

import dagger.Binds
import dagger.Module
import pl.kamilszustak.justfit.domain.usecase.activity.GetActivityByIdUseCase
import pl.kamilszustak.justfit.domain.usecase.activity.GetActivityByIdUseCaseImpl
import pl.kamilszustak.justfit.domain.usecase.activity.GetAllActivitiesUseCase
import pl.kamilszustak.justfit.domain.usecase.activity.GetAllActivitiesUseCaseImpl
import pl.kamilszustak.justfit.domain.usecase.activity.JoinInActivityUseCase
import pl.kamilszustak.justfit.domain.usecase.activity.JoinInActivityUseCaseImpl
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
import pl.kamilszustak.justfit.domain.usecase.product.BuyProductUseCase
import pl.kamilszustak.justfit.domain.usecase.product.BuyProductUseCaseImpl
import pl.kamilszustak.justfit.domain.usecase.product.GetAllClientProductsUseCase
import pl.kamilszustak.justfit.domain.usecase.product.GetAllClientProductsUseCaseImpl
import pl.kamilszustak.justfit.domain.usecase.product.GetAllProductsUseCase
import pl.kamilszustak.justfit.domain.usecase.product.GetAllProductsUseCaseImpl
import pl.kamilszustak.justfit.domain.usecase.product.GetProductByIdUseCase
import pl.kamilszustak.justfit.domain.usecase.product.GetProductByIdUseCaseImpl
import pl.kamilszustak.justfit.domain.usecase.user.GetUserUseCase
import pl.kamilszustak.justfit.domain.usecase.user.GetUserUseCaseImpl
import pl.kamilszustak.justfit.domain.usecase.user.IsUserAuthenticated
import pl.kamilszustak.justfit.domain.usecase.user.IsUserAuthenticatedImpl
import pl.kamilszustak.justfit.domain.usecase.user.LogoutUserUseCase
import pl.kamilszustak.justfit.domain.usecase.user.LogoutUserUseCaseImpl

@Module
interface UseCaseModule {
    @Binds
    fun bindGetUserUseCase(useCaseImpl: GetUserUseCaseImpl): GetUserUseCase

    @Binds
    fun bindClearDatabaseUserCase(useCaseImpl: ClearDatabaseUseCaseImpl): ClearDatabaseUseCase

    @Binds
    fun bindIsUserAuthenticated(useCaseImpl: IsUserAuthenticatedImpl): IsUserAuthenticated

    @Binds
    fun bindLogoutUserUseCase(useCaseImpl: LogoutUserUseCaseImpl): LogoutUserUseCase

    @Binds
    fun bindGetAllEquipmentUseCase(useCaseImpl: GetAllEquipmentUseCaseImpl): GetAllEquipmentUseCase

    @Binds
    fun bindGetAllAvailableEquipmentUseCase(useCaseImpl: GetAllAvailableEquipmentUseCaseImpl): GetAllAvailableEquipmentUseCase

    @Binds
    fun bindSetEquipmentAsUnavailableUseCase(useCaseImpl: SetEquipmentAsUnavailableUseCaseImpl): SetEquipmentAsUnavailableUseCase

    @Binds
    fun bindGetAllEmployeesUseCase(useCaseImpl: GetAllEmployeesUseCaseImpl): GetAllEmployeesUseCase

    @Binds
    fun bindGetEmployeeByIdUseCase(useCaseImpl: GetEmployeeByIdUseCaseImpl): GetEmployeeByIdUseCase

    @Binds
    fun bindGetAllEventsUseCase(useCaseImpl: GetAllEventsUseCaseImpl): GetAllEventsUseCase

    @Binds
    fun bindGetEventByIdUseCase(useCaseImpl: GetEventByIdUseCaseImpl): GetEventByIdUseCase

    @Binds
    fun bindGetAllProductsUseCase(useCaseImpl: GetAllProductsUseCaseImpl): GetAllProductsUseCase

    @Binds
    fun bindGetProductByIdUseCase(useCaseImpl: GetProductByIdUseCaseImpl): GetProductByIdUseCase

    @Binds
    fun bindBuyProductUseCase(useCaseImpl: BuyProductUseCaseImpl): BuyProductUseCase

    @Binds
    fun bindGetAllClientProductsUseCase(useCaseImpl: GetAllClientProductsUseCaseImpl): GetAllClientProductsUseCase

    @Binds
    fun bindGetAllActivitiesUseCase(useCaseImpl: GetAllActivitiesUseCaseImpl): GetAllActivitiesUseCase

    @Binds
    fun bindGetActivityByIdUseCase(useCaseImpl: GetActivityByIdUseCaseImpl): GetActivityByIdUseCase

    @Binds
    fun bindJoinInActivityUseCase(useCaseImpl: JoinInActivityUseCaseImpl): JoinInActivityUseCase
}