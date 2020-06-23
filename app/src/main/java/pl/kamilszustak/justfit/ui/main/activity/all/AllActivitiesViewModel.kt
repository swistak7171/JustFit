package pl.kamilszustak.justfit.ui.main.activity.all

import android.app.Application
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.common.livedata.ResourceDataSource
import pl.kamilszustak.justfit.domain.model.activity.Activity
import pl.kamilszustak.justfit.domain.usecase.activity.GetAllActivitiesUseCase
import pl.kamilszustak.justfit.domain.usecase.activity.JoinInActivityUseCase
import pl.kamilszustak.justfit.ui.base.StateViewModel
import javax.inject.Inject

class AllActivitiesViewModel @Inject constructor(
    application: Application,
    private val getAllActivities: GetAllActivitiesUseCase,
    private val joinInActivity: JoinInActivityUseCase
) : StateViewModel(application) {

    val activitiesResource: ResourceDataSource<List<Activity>> = ResourceDataSource()

    init {
        activitiesResource.setFlowSource {
            getAllActivities()
        }
    }

    fun onRefresh() {
        activitiesResource.refresh()
    }

    fun onJoinInButtonClick(activityId: Long) {
        performAction(R.string.joining_in_activity_error_message) {
            joinInActivity(activityId)
        }
    }
}