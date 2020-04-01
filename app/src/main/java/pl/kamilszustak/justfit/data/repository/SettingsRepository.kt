package pl.kamilszustak.justfit.data.repository

import android.app.Application
import pl.kamilszustak.justfit.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Suppress("UNCHECKED_CAST")
class SettingsRepository @Inject constructor(
    application: Application
) : SharedPreferencesRepository<SettingsRepository.SettingsKey>(
    application,
    "settings_shared_preferences"
) {
    enum class SettingsKey : SharedPreferencesKey {
        IS_USER_LOGGED_IN {
            override fun getStringResourceId(): Int =
                R.string.shared_preferences_is_user_logged_in

            override fun <T : Comparable<T>> getDefaultValue(): T =
                DEFAULT_IS_USER_LOGGED_IN as T
        }
    }

    companion object {
        const val DEFAULT_IS_USER_LOGGED_IN: Boolean = false
    }
}