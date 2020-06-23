package pl.kamilszustak.justfit.ui.main.event.details

import android.app.Application
import pl.kamilszustak.justfit.common.livedata.ResourceDataSource
import pl.kamilszustak.justfit.domain.model.employee.Employee
import pl.kamilszustak.justfit.domain.model.event.Event
import pl.kamilszustak.justfit.domain.usecase.employee.GetEmployeeByIdUseCase
import pl.kamilszustak.justfit.domain.usecase.event.GetEventByIdUseCase
import pl.kamilszustak.justfit.ui.base.BaseViewModel
import javax.inject.Inject

class EventDetailsViewModel @Inject constructor(
    application: Application,
    private val getEventById: GetEventByIdUseCase,
    private val getEmployeeById: GetEmployeeByIdUseCase
) : BaseViewModel(application) {

    val eventResource: ResourceDataSource<Event> = ResourceDataSource()
    val employeeResource: ResourceDataSource<Employee> = ResourceDataSource()

    init {
        employeeResource.result.addSource(eventResource.result) { resource ->
            if (resource != null && resource.isSuccess && resource.data != null) {
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
}