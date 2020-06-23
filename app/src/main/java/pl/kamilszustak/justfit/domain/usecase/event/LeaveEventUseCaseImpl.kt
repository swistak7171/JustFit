package pl.kamilszustak.justfit.domain.usecase.event

import pl.kamilszustak.justfit.data.repository.EventRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LeaveEventUseCaseImpl @Inject constructor(
    private val eventRepository: EventRepository
) : LeaveEventUseCase {

    override suspend fun invoke(eventId: Long): Result<Unit> =
        eventRepository.leave(eventId)
}