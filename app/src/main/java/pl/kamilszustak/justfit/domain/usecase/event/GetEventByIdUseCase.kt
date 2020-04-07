package pl.kamilszustak.justfit.domain.usecase.event

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.justfit.common.data.Resource
import pl.kamilszustak.justfit.domain.model.event.Event

interface GetEventByIdUseCase {
    operator fun invoke(id: Long): Flow<Resource<Event>>
}