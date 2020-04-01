package pl.kamilszustak.justfit.ui.main.profile

import android.app.Application
import pl.kamilszustak.justfit.common.livedata.ResourceDataSource
import pl.kamilszustak.justfit.domain.model.user.User
import pl.kamilszustak.justfit.domain.usecase.user.GetUserUseCase
import pl.kamilszustak.justfit.ui.base.BaseViewModel
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    application: Application,
    private val getUserUseCase: GetUserUseCase
) : BaseViewModel(application) {

    val userResource: ResourceDataSource<User> = ResourceDataSource()

    init {
        userResource.changeFlowSource {
            getUserUseCase()
        }
    }

    fun onRefresh() {
        userResource.refresh()
    }
}