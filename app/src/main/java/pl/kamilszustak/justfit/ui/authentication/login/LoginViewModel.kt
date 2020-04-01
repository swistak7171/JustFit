package pl.kamilszustak.justfit.ui.authentication.login

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.common.livedata.SingleLiveData
import pl.kamilszustak.justfit.common.livedata.UniqueLiveData
import pl.kamilszustak.justfit.domain.manager.AuthenticationManager
import pl.kamilszustak.justfit.ui.base.BaseViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    application: Application,
    private val authenticationManager: AuthenticationManager
) : BaseViewModel(application) {

    val userEmail: UniqueLiveData<String> = UniqueLiveData()
    val userPassword: UniqueLiveData<String> = UniqueLiveData()

    private val _completed: SingleLiveData<Unit> = SingleLiveData()
    val completed: LiveData<Unit> = _completed

    private val _isLoading: UniqueLiveData<Boolean> = UniqueLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error: SingleLiveData<Int> = SingleLiveData()
    val error: LiveData<Int> = _error

    fun onLoginButtonClick() {
        val email = userEmail.value
        val password = userPassword.value

        if (email.isNullOrBlank()) {
            _error.value = R.string.empty_user_email_error_message
            return
        }

        if (password.isNullOrBlank()) {
            _error.value = R.string.empty_user_password_error_message
            return
        }

        viewModelScope.launch(Dispatchers.Main) {
            _isLoading.value = true

            val result = authenticationManager.login(email, password)
            result.onSuccess {
                _completed.call()
            }.onFailure {
                _error.value = R.string.authentication_error_message
            }

            _isLoading.value = false
        }
    }
}