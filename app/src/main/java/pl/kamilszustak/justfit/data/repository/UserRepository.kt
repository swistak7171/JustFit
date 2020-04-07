package pl.kamilszustak.justfit.data.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.justfit.common.data.NetworkBoundResource
import pl.kamilszustak.justfit.common.data.Resource
import pl.kamilszustak.justfit.data.database.dao.UserDao
import pl.kamilszustak.justfit.domain.mapper.UserJsonMapper
import pl.kamilszustak.justfit.domain.model.user.User
import pl.kamilszustak.justfit.domain.model.user.UserJson
import pl.kamilszustak.justfit.network.service.ClientApiService
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao,
    private val clientApiService: ClientApiService,
    private val userDetailsRepository: UserDetailsRepository,
    private val userJsonMapper: UserJsonMapper
) {
    fun getLoggedIn(shouldFetch: Boolean = true): Flow<Resource<User>> {
        val userId = userDetailsRepository.getValue<Long>(UserDetailsRepository.UserDetailsKey.USER_ID)

        return object : NetworkBoundResource<UserJson, User>() {
            override fun loadFromDatabase(): Flow<User> =
                userDao.getById(userId)

            override fun shouldFetch(data: User?): Boolean = shouldFetch

            override suspend fun fetchFromNetwork(): Response<UserJson> =
                clientApiService.getLoggedIn()

            override suspend fun saveFetchResult(result: UserJson) {
                userJsonMapper.onMap(result) { user ->
                    userDao.insert(user)
                    userDetailsRepository.setValue(UserDetailsRepository.UserDetailsKey.USER_ID to user.id)
                }
            }
        }.asFlow()
    }
}