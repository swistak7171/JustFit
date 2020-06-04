package pl.kamilszustak.justfit.ui.main.activity.details

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import pl.kamilszustak.justfit.common.date.DateFormats
import pl.kamilszustak.justfit.common.livedata.ResourceDataSource
import pl.kamilszustak.justfit.domain.model.activity.Activity
import pl.kamilszustak.justfit.domain.usecase.activity.GetActivityByIdUseCase
import pl.kamilszustak.justfit.ui.base.StateViewModel
import javax.inject.Inject

class ActivityDetailsViewModel @Inject constructor(
    application: Application,
    private val getActivityById: GetActivityByIdUseCase
) : StateViewModel(application) {

    val activityResource: ResourceDataSource<Activity> = ResourceDataSource()
    val dateText: LiveData<String> = activityResource.data.map { activity ->
        DateFormats.simpleDateFormat.format(activity.startDate)
    }

    val timeText: LiveData<Pair<String, String>> = activityResource.data.map { activity ->
        val startTimeText = DateFormats.simpleHourFormat.format(activity.startDate)
        val endTimeText = DateFormats.simpleHourFormat.format(activity.endDate)

        startTimeText to endTimeText
    }

    fun loadData(activityId: Long) {
        initialize {
            activityResource.setFlowSource {
                getActivityById(activityId)
            }
        }
    }

    fun onRefresh() {
        activityResource.refresh()
    }
}