package pl.kamilszustak.justfit.data.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.justfit.common.data.NetworkBoundResource
import pl.kamilszustak.justfit.common.data.Resource
import pl.kamilszustak.justfit.data.database.dao.EmployeeDao
import pl.kamilszustak.justfit.domain.mapper.EmployeeJsonMapper
import pl.kamilszustak.justfit.domain.model.employee.Employee
import pl.kamilszustak.justfit.domain.model.employee.EmployeeJson
import pl.kamilszustak.justfit.network.service.EmployeeApiService
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmployeeRepository @Inject constructor(
    private val employeeDao: EmployeeDao,
    private val employeeApiService: EmployeeApiService,
    private val employeeJsonMapper: EmployeeJsonMapper
) {
    fun getAll(shouldFetch: Boolean = true): Flow<Resource<List<Employee>>> {
        return object : NetworkBoundResource<List<EmployeeJson>, List<Employee>>() {
            override fun loadFromDatabase(): Flow<List<Employee>> =
                employeeDao.getAll()

            override fun shouldFetch(data: List<Employee>?): Boolean = shouldFetch

            override suspend fun fetchFromNetwork(): Response<List<EmployeeJson>> =
                employeeApiService.getAllEmployees()

            override suspend fun saveFetchResult(result: List<EmployeeJson>) {
                employeeJsonMapper.onMapAll(result) { employees ->
                    employeeDao.replaceAll(employees)
                }
            }
        }.asFlow()
    }

    fun getById(id: Long, shouldFetch: Boolean = true): Flow<Resource<Employee>> {
        return object : NetworkBoundResource<EmployeeJson, Employee>() {
            override fun loadFromDatabase(): Flow<Employee> =
                employeeDao.getById(id)

            override fun shouldFetch(data: Employee?): Boolean = shouldFetch

            override suspend fun fetchFromNetwork(): Response<EmployeeJson> =
                employeeApiService.getEmployeeById(id)

            override suspend fun saveFetchResult(result: EmployeeJson) {
                employeeJsonMapper.onMap(result) { employee ->
                    employeeDao.insert(employee)
                }
            }
        }.asFlow()
    }
}