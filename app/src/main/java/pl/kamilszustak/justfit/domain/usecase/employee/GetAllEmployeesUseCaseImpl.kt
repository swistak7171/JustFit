package pl.kamilszustak.justfit.domain.usecase.employee

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.justfit.common.data.Resource
import pl.kamilszustak.justfit.data.repository.EmployeeRepository
import pl.kamilszustak.justfit.domain.model.employee.Employee
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllEmployeesUseCaseImpl @Inject constructor(
    private val employeeRepository: EmployeeRepository
) : GetAllEmployeesUseCase {

    override fun invoke(): Flow<Resource<List<Employee>>> =
        employeeRepository.getAll()
}