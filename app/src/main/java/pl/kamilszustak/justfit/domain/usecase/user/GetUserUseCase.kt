package pl.kamilszustak.justfit.domain.usecase.user

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.justfit.common.data.Resource
import pl.kamilszustak.justfit.domain.model.user.User

interface GetUserUseCase {
    operator fun invoke(): Flow<Resource<User>>
}