package pl.kamilszustak.justfit.ui.main.profile

import android.app.Application
import androidx.lifecycle.LiveData
import pl.kamilszustak.justfit.common.livedata.ResourceDataSource
import pl.kamilszustak.justfit.common.livedata.SingleLiveData
import pl.kamilszustak.justfit.domain.model.user.User
import pl.kamilszustak.justfit.domain.usecase.user.GetUserUseCase
import pl.kamilszustak.justfit.domain.usecase.user.LogoutUserUseCase
import pl.kamilszustak.justfit.ui.base.BaseViewModel
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    application: Application,
    private val getUserUseCase: GetUserUseCase,
    private val logoutUserUseCase: LogoutUserUseCase
) : BaseViewModel(application) {

    val userResource: ResourceDataSource<User> = ResourceDataSource()

    private val _logoutCompleted: SingleLiveData<Unit> = SingleLiveData()
    val logoutCompleted: LiveData<Unit> = _logoutCompleted

    init {
        userResource.changeFlowSource {
            getUserUseCase()
        }
    }

    fun onRefresh() {
        userResource.refresh()
    }

    fun onLogoutButtonClick() {
        logoutUserUseCase()
        _logoutCompleted.call()
    }
}