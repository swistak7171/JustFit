package pl.kamilszustak.justfit.ui.main.activity.details

import android.app.Application
import pl.kamilszustak.justfit.domain.usecase.activity.GetActivityByIdUseCase
import pl.kamilszustak.justfit.ui.base.BaseViewModel
import javax.inject.Inject

class ActivityDetailsViewModel @Inject constructor(
    application: Application,
    private val getActivityById: GetActivityByIdUseCase
) : BaseViewModel(application) {
}