package pl.kamilszustak.justfit.ui.main.event

import android.app.Application
import pl.kamilszustak.justfit.common.livedata.ResourceDataSource
import pl.kamilszustak.justfit.domain.model.event.Event
import pl.kamilszustak.justfit.domain.usecase.event.GetAllEventsUseCase
import pl.kamilszustak.justfit.ui.base.BaseViewModel
import javax.inject.Inject

class EventsViewModel @Inject constructor(
    application: Application,
    private val getAllEventsUseCase: GetAllEventsUseCase
) : BaseViewModel(application) {

    val eventsResource: ResourceDataSource<List<Event>> = ResourceDataSource()

    init {
        eventsResource.changeFlowSource {
            getAllEventsUseCase()
        }
    }

    fun onRefresh() {
        eventsResource.refresh()
    }
}