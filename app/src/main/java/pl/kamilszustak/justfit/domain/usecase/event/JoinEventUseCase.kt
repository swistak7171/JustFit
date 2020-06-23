package pl.kamilszustak.justfit.domain.usecase.event

interface JoinEventUseCase {
    suspend operator fun invoke(eventId: Long): Result<Unit>
}