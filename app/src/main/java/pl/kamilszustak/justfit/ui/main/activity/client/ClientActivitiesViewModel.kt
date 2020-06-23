package pl.kamilszustak.justfit.ui.main.activity.client

import android.app.Application
import pl.kamilszustak.justfit.common.livedata.ResourceDataSource
import pl.kamilszustak.justfit.domain.model.activity.Activity
import pl.kamilszustak.justfit.domain.usecase.activity.GetAllClientActivitiesUseCase
import pl.kamilszustak.justfit.ui.base.BaseViewModel
import javax.inject.Inject

class ClientActivitiesViewModel @Inject constructor(
    application: Application,
    private val getAllClientActivities: GetAllClientActivitiesUseCase
) : BaseViewModel(application) {

    val activitiesResource: ResourceDataSource<List<Activity>> = ResourceDataSource()

    init {
        activitiesResource.setFlowSource {
            getAllClientActivities()
        }
    }

    fun onRefresh() {
        activitiesResource.refresh()
    }
}