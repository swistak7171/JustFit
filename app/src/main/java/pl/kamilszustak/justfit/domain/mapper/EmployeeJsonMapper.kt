package pl.kamilszustak.justfit.domain.mapper

import pl.kamilszustak.justfit.domain.model.employee.EmployeeEntity
import pl.kamilszustak.justfit.domain.model.employee.EmployeeJson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmployeeJsonMapper @Inject constructor() : JsonModelMapper<EmployeeJson, EmployeeEntity>() {
    override fun map(model: EmployeeJson): EmployeeEntity = EmployeeEntity(
        name = model.name,
        surname = model.surname,
        email = model.email,
        phoneNumber = model.phoneNumber,
        specialization = model.specialization,
        workHours = model.workHours
    )
}