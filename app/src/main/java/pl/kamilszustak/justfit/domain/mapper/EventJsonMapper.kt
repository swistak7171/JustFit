package pl.kamilszustak.justfit.domain.mapper

import pl.kamilszustak.justfit.domain.model.event.Event
import pl.kamilszustak.justfit.domain.model.event.EventJson
import javax.inject.Singleton

@Singleton
class EventJsonMapper : JsonModelMapper<EventJson, Event>() {
    override fun map(model: EventJson): Event = Event(
        employeeId = model.employeeId,
        name = model.name,
        startDate = model.startDate,
        endDate = model.endDate,
        numberOfAttendees = model.clientsIds.size
    )
}