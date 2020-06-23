package pl.kamilszustak.justfit.ui.main.employee

import android.app.Application
import pl.kamilszustak.justfit.common.livedata.ResourceDataSource
import pl.kamilszustak.justfit.domain.model.employee.Employee
import pl.kamilszustak.justfit.domain.usecase.employee.GetAllEmployeesUseCase
import pl.kamilszustak.justfit.ui.base.BaseViewModel
import javax.inject.Inject

class EmployeesViewModel @Inject constructor(
    application: Application,
    private val getAllEmployeesUseCase: GetAllEmployeesUseCase
) : BaseViewModel(application) {
    val employeesResource: ResourceDataSource<List<Employee>> = ResourceDataSource()

    init {
        employeesResource.setFlowSource {
            getAllEmployeesUseCase()
        }
    }

    fun onRefresh() {
        employeesResource.refresh()
    }
}