package pl.kamilszustak.justfit.ui.main.employee.details

import android.app.Application
import pl.kamilszustak.justfit.common.livedata.ResourceDataSource
import pl.kamilszustak.justfit.domain.model.employee.Employee
import pl.kamilszustak.justfit.domain.usecase.employee.GetEmployeeByIdUseCase
import pl.kamilszustak.justfit.ui.base.BaseViewModel
import javax.inject.Inject

class EmployeeDetailsViewModel @Inject constructor(
    application: Application,
    private val getEmployeeByIdUseCase: GetEmployeeByIdUseCase
) : BaseViewModel(application) {

    val employeeResource: ResourceDataSource<Employee> = ResourceDataSource()

    fun loadData(employeeId: Long) {
        employeeResource.changeFlowSource {
            getEmployeeByIdUseCase(employeeId)
        }
    }
}