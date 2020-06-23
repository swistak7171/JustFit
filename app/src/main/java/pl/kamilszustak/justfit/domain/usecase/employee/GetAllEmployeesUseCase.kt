package pl.kamilszustak.justfit.domain.usecase.employee

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.justfit.common.data.Resource
import pl.kamilszustak.justfit.domain.model.employee.Employee

interface GetAllEmployeesUseCase {
    operator fun invoke(): Flow<Resource<List<Employee>>>
}