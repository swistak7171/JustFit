package pl.kamilszustak.justfit.domain.usecase.event

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.justfit.common.data.Resource
import pl.kamilszustak.justfit.data.repository.EventRepository
import pl.kamilszustak.justfit.domain.model.event.Event
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllEventsUseCaseImpl @Inject constructor(
    private val eventRepository: EventRepository
) : GetAllEventsUseCase {

    override fun invoke(): Flow<Resource<List<Event>>> =
        eventRepository.getAll()
}