package pl.kamilszustak.justfit.domain.usecase.event

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.justfit.common.data.Resource
import pl.kamilszustak.justfit.data.repository.EventRepository
import pl.kamilszustak.justfit.domain.model.event.Event
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetEventByIdUseCaseImpl @Inject constructor(
    private val eventRepository: EventRepository
) : GetEventByIdUseCase {

    override fun invoke(id: Long): Flow<Resource<Event>> =
        eventRepository.getById(id)
}