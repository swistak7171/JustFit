package pl.kamilszustak.justfit.domain.usecase.user

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.justfit.common.data.Resource
import pl.kamilszustak.justfit.data.repository.UserRepository
import pl.kamilszustak.justfit.domain.model.user.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : GetUserUseCase {

    override fun invoke(): Flow<Resource<User>> = userRepository.getLoggedIn()
}