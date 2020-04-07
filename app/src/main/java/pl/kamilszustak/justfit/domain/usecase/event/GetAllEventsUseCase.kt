package pl.kamilszustak.justfit.domain.usecase.event

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.justfit.common.data.Resource
import pl.kamilszustak.justfit.domain.model.event.Event

interface GetAllEventsUseCase {
    operator fun invoke(): Flow<Resource<List<Event>>>
}