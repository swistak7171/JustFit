package pl.kamilszustak.justfit.data.repository

import android.app.Application
import androidx.core.content.edit
import pl.kamilszustak.justfit.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Suppress("UNCHECKED_CAST")
class UserDetailsRepository @Inject constructor(
    application: Application
) : SharedPreferencesRepository<UserDetailsRepository.UserDetailsKey>(
    application,
    "user_details_shared_preferences"
) {

    override fun restoreDefaultValues() {
        sharedPreferences.edit {
            clear()
        }
    }

    enum class UserDetailsKey : SharedPreferencesKey {
        USER_EMAIL {
            override fun getStringResourceId(): Int =
                R.string.shared_preferences_user_email

            override fun <T : Comparable<T>> getDefaultValue(): T =
                DEFAULT_USER_EMAIL as T
        },

        USER_PASSWORD {
            override fun getStringResourceId(): Int =
                R.string.shared_preferences_user_password

            override fun <T : Comparable<T>> getDefaultValue(): T =
                DEFAULT_USER_PASSWORD as T
        },

        USER_ID {
            override fun getStringResourceId(): Int =
                R.string.shared_preferences_user_id

            override fun <T : Comparable<T>> getDefaultValue(): T =
                DEFAULT_USER_ID as T
        }
    }

    companion object {
        const val DEFAULT_USER_EMAIL: String = "test@wp.pl"
        const val DEFAULT_USER_PASSWORD: String = "password"
        const val DEFAULT_USER_ID: Long = 0
    }
}