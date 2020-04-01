package pl.kamilszustak.justfit.domain.usecase.user

import pl.kamilszustak.justfit.data.repository.SettingsRepository
import javax.inject.Inject

class IsUserAuthenticatedImpl @Inject constructor(
    private val settingsRepository: SettingsRepository
) : IsUserAuthenticated {

    override fun invoke(): Boolean = settingsRepository.getValue(
        SettingsRepository.SettingsKey.IS_USER_AUTHENTICATED
    )
}