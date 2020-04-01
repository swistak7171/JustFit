package pl.kamilszustak.justfit.di.module

import dagger.Binds
import dagger.Module
import pl.kamilszustak.justfit.domain.usecase.database.ClearDatabaseUseCase
import pl.kamilszustak.justfit.domain.usecase.database.ClearDatabaseUseCaseImpl
import pl.kamilszustak.justfit.domain.usecase.user.GetUserUseCase
import pl.kamilszustak.justfit.domain.usecase.user.GetUserUseCaseImpl
import pl.kamilszustak.justfit.domain.usecase.user.IsUserAuthenticated
import pl.kamilszustak.justfit.domain.usecase.user.IsUserAuthenticatedImpl
import pl.kamilszustak.justfit.domain.usecase.user.LogoutUserUseCase
import pl.kamilszustak.justfit.domain.usecase.user.LogoutUserUseCaseImpl

@Module
abstract class UseCaseModule {
    @Binds
    abstract fun bindGetUserUseCase(getUserUseCaseImpl: GetUserUseCaseImpl): GetUserUseCase

    @Binds
    abstract fun bindClearDatabaseUserCase(clearDatabaseUseCaseImpl: ClearDatabaseUseCaseImpl): ClearDatabaseUseCase

    @Binds
    abstract fun bindIsUserAuthenticated(isUserAuthenticatedUseCaseImpl: IsUserAuthenticatedImpl): IsUserAuthenticated

    @Binds
    abstract fun logoutUserUseCase(logoutUserUseCaseImpl: LogoutUserUseCaseImpl): LogoutUserUseCase
}