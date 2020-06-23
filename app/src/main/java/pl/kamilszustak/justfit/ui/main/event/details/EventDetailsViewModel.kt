package pl.kamilszustak.justfit.ui.main.event.details

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.common.livedata.ResourceDataSource
import pl.kamilszustak.justfit.domain.model.employee.Employee
import pl.kamilszustak.justfit.domain.model.event.Event
import pl.kamilszustak.justfit.domain.usecase.employee.GetEmployeeByIdUseCase
import pl.kamilszustak.justfit.domain.usecase.event.GetEventByIdUseCase
import pl.kamilszustak.justfit.domain.usecase.event.JoinEventUseCase
import pl.kamilszustak.justfit.domain.usecase.event.LeaveEventUseCase
import pl.kamilszustak.justfit.ui.base.StateViewModel
import javax.inject.Inject

class EventDetailsViewModel @Inject constructor(
    application: Application,
    private val getEventById: GetEventByIdUseCase,
    private val getEmployeeById: GetEmployeeByIdUseCase,
    private val joinEvent: JoinEventUseCase,
    private val leaveEvent: LeaveEventUseCase
) : StateViewModel(application) {

    val eventResource: ResourceDataSource<Event> = ResourceDataSource()
    val employeeResource: ResourceDataSource<Employee> = ResourceDataSource()
    val numberOfAttendees: LiveData<String> = eventResource.result.map { resource ->
        if (resource?.data != null) {
            resource.data.numberOfAttendees.toString()
        } else {
            ""
        }
    }

    init {
        employeeResource.result.addSource(eventResource.result) { resource ->
            if (resource?.data != null) {
                employeeResource.setFlowSource {
                    getEmployeeById(resource.data.employeeId)
                }
            }
        }
    }

    fun loadData(eventId: Long) {
        eventResource.setFlowSource {
            getEventById(eventId)
        }
    }

    fun onRefresh() {
        eventResource.refresh()
    }

    fun onJoinButtonClick(eventId: Long) {
        performAction(R.string.joining_event_error_message) {
            joinEvent(eventId)
        }
    }

    fun onLeaveButtonClick(eventId: Long) {
        performAction(R.string.leaving_event_error_message) {
            leaveEvent(eventId)
        }
    }
}