package pl.kamilszustak.justfit.domain.usecase.employee

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.justfit.common.data.Resource
import pl.kamilszustak.justfit.data.repository.EmployeeRepository
import pl.kamilszustak.justfit.domain.model.employee.Employee
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetEmployeeByIdUseCaseImpl @Inject constructor(
    private val employeeRepository: EmployeeRepository
) : GetEmployeeByIdUseCase {

    override fun invoke(id: Long): Flow<Resource<Employee>> =
        employeeRepository.getById(id)
}