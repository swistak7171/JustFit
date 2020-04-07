package pl.kamilszustak.justfit.data.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.justfit.common.data.NetworkBoundResource
import pl.kamilszustak.justfit.common.data.Resource
import pl.kamilszustak.justfit.data.database.dao.EventDao
import pl.kamilszustak.justfit.domain.mapper.EventJsonMapper
import pl.kamilszustak.justfit.domain.model.event.Event
import pl.kamilszustak.justfit.domain.model.event.EventJson
import pl.kamilszustak.justfit.network.service.EventApiService
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventRepository @Inject constructor(
    private val eventDao: EventDao,
    private val eventApiService: EventApiService,
    private val eventJsonMapper: EventJsonMapper
) {
    fun getAll(shouldFetch: Boolean = true): Flow<Resource<List<Event>>> {
        return object : NetworkBoundResource<List<EventJson>, List<Event>>() {
            override fun loadFromDatabase(): Flow<List<Event>> =
                eventDao.getAll()

            override fun shouldFetch(data: List<Event>?): Boolean = shouldFetch

            override suspend fun fetchFromNetwork(): Response<List<EventJson>> =
                eventApiService.getAll()

            override suspend fun saveFetchResult(result: List<EventJson>) {
                eventJsonMapper.onMapAll(result) { events ->
                    eventDao.replaceAll(events)
                }
            }
        }.asFlow()
    }
}