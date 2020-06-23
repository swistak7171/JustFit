package pl.kamilszustak.justfit.domain.usecase.event

import pl.kamilszustak.justfit.data.repository.EventRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JoinEventUseCaseImpl @Inject constructor(
    private val eventRepository: EventRepository
) : JoinEventUseCase {

    override suspend fun invoke(eventId: Long): Result<Unit> =
        eventRepository.join(eventId)
}