package pl.kamilszustak.justfit.domain.usecase.user

import pl.kamilszustak.justfit.data.repository.SettingsRepository
import pl.kamilszustak.justfit.data.repository.UserDetailsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogoutUserUseCaseImpl @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val userDetailsRepository: UserDetailsRepository
) : LogoutUserUseCase {

    override fun invoke() {
        settingsRepository.setValue(SettingsRepository.SettingsKey.IS_USER_AUTHENTICATED to false)
        userDetailsRepository.restoreDefaultValues()
    }
}