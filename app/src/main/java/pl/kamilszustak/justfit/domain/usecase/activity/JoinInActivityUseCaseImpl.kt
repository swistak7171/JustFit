package pl.kamilszustak.justfit.domain.usecase.activity

import pl.kamilszustak.justfit.data.repository.ActivityRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JoinInActivityUseCaseImpl @Inject constructor(
    private val activityRepository: ActivityRepository
) : JoinInActivityUseCase {

    override suspend fun invoke(activityId: Long): Result<Unit> =
        activityRepository.joinInById(activityId)
}