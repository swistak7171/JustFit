package pl.kamilszustak.justfit.ui.main.activity

import android.app.Application
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.common.livedata.ResourceDataSource
import pl.kamilszustak.justfit.domain.model.activity.Activity
import pl.kamilszustak.justfit.domain.usecase.activity.GetAllActivitiesByDateUseCase
import pl.kamilszustak.justfit.domain.usecase.activity.GetAllActivitiesUseCase
import pl.kamilszustak.justfit.domain.usecase.activity.JoinInActivityUseCase
import pl.kamilszustak.justfit.ui.base.StateViewModel
import java.util.Date
import javax.inject.Inject

class ActivitiesViewModel @Inject constructor(
    application: Application,
    private val getAllActivitiesByDate: GetAllActivitiesByDateUseCase,
    private val getAllActivities: GetAllActivitiesUseCase,
    private val joinInActivity: JoinInActivityUseCase
) : StateViewModel(application) {

    val activitiesResource: ResourceDataSource<List<Activity>> = ResourceDataSource()

    fun onRefresh() {
        activitiesResource.refresh()
    }

    fun onDateSelected(date: Date) {
        activitiesResource.setFlowSource {
            getAllActivitiesByDate(date)
        }
    }

    fun onJoinInButtonClick(activityId: Long) {
        performAction(R.string.joining_in_activity_error_message) {
            joinInActivity(activityId)
        }
    }
}