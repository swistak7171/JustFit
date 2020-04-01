package pl.kamilszustak.justfit.domain.usecase.database

interface ClearDatabaseUseCase {
    suspend operator fun invoke()
}