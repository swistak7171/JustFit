package pl.kamilszustak.justfit.ui.main.activity

import android.app.Application
import pl.kamilszustak.justfit.common.livedata.ResourceDataSource
import pl.kamilszustak.justfit.domain.model.activity.Activity
import pl.kamilszustak.justfit.domain.usecase.activity.GetAllActivitiesUseCase
import pl.kamilszustak.justfit.ui.base.BaseViewModel
import javax.inject.Inject

class ActivitiesViewModel @Inject constructor(
    application: Application,
    private val getAllActivities: GetAllActivitiesUseCase
) : BaseViewModel(application) {

    val activitiesResource: ResourceDataSource<List<Activity>> = ResourceDataSource()

    init {
        activitiesResource.setFlowSource {
            getAllActivities()
        }
    }
}