package pl.kamilszustak.justfit.domain.usecase.activity

interface JoinInActivityUseCase {
    suspend operator fun invoke(activityId: Long): Result<Unit>
}