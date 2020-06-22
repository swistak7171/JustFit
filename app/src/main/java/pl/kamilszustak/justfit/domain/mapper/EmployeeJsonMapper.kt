package pl.kamilszustak.justfit.domain.mapper

import pl.kamilszustak.justfit.domain.model.employee.Employee
import pl.kamilszustak.justfit.domain.model.employee.EmployeeJson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmployeeJsonMapper @Inject constructor() : JsonModelMapper<EmployeeJson, Employee>() {
    override fun map(model: EmployeeJson): Employee = Employee(
        name = model.name,
        surname = model.surname,
        email = model.email,
        phoneNumber = model.phoneNumber,
        specialization = model.specialization,
        workHours = model.workHours
    ).apply {
        this.id = model.id
    }
}