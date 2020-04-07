package pl.kamilszustak.justfit.domain.manager

import pl.kamilszustak.justfit.common.data.NetworkCall
import pl.kamilszustak.justfit.data.database.dao.UserDao
import pl.kamilszustak.justfit.data.repository.SettingsRepository
import pl.kamilszustak.justfit.data.repository.UserDetailsRepository
import pl.kamilszustak.justfit.domain.mapper.UserJsonMapper
import pl.kamilszustak.justfit.domain.model.user.UserJson
import pl.kamilszustak.justfit.domain.usecase.database.ClearDatabaseUseCase
import pl.kamilszustak.justfit.network.service.ClientApiService
import pl.kamilszustak.justfit.util.withIOContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationManager @Inject constructor(
    private val clientApiService: ClientApiService,
    private val userDao: UserDao,
    private val userDetailsRepository: UserDetailsRepository,
    private val userJsonMapper: UserJsonMapper,
    private val settingsRepository: SettingsRepository,
    private val clearDatabaseUseCase: ClearDatabaseUseCase
) {
    suspend fun login(email: String, password: String): Result<Unit> {
        userDetailsRepository.setValues(
            UserDetailsRepository.UserDetailsKey.USER_EMAIL to email,
            UserDetailsRepository.UserDetailsKey.USER_PASSWORD to password
        )

        return withIOContext {
            object : NetworkCall<UserJson, Unit>() {
                override suspend fun makeCall(): Response<UserJson> =
                    clientApiService.getLoggedIn()

                override suspend fun mapResponse(response: UserJson): Unit = Unit

                override suspend fun saveCallResult(result: UserJson) {
                    userJsonMapper.onMap(result) { user ->
                        userDao.insert(user)
                        userDetailsRepository.setValue(UserDetailsRepository.UserDetailsKey.USER_ID to user.id)
                        settingsRepository.setValue(SettingsRepository.SettingsKey.IS_USER_AUTHENTICATED to true)
                    }
                }
            }.callForResponse()
        }
    }

    suspend fun logout() {
        withIOContext {
            clearDatabaseUseCase()
            userDetailsRepository.restoreDefaultValues()
            settingsRepository.restoreDefaultValues()
        }
    }
}