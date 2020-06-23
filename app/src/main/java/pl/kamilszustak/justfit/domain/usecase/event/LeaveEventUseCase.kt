package pl.kamilszustak.justfit.domain.usecase.event

interface LeaveEventUseCase {
    suspend operator fun invoke(eventId: Long): Result<Unit>
}