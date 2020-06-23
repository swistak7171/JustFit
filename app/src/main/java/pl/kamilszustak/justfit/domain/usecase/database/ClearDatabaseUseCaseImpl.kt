package pl.kamilszustak.justfit.domain.usecase.database

import pl.kamilszustak.justfit.data.database.ApplicationDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClearDatabaseUseCaseImpl @Inject constructor(
    private val applicationDatabase: ApplicationDatabase
) : ClearDatabaseUseCase {

    override suspend fun invoke() {
        applicationDatabase.clearAllTables()
    }
}