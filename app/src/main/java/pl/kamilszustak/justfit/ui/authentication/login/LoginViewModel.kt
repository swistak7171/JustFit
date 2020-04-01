package pl.kamilszustak.justfit.ui.authentication.login

import android.app.Application
import pl.kamilszustak.justfit.domain.manager.AuthenticationManager
import pl.kamilszustak.justfit.ui.base.BaseViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    application: Application,
    private val authenticationManager: AuthenticationManager
) : BaseViewModel(application) {
}