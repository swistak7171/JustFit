package pl.kamilszustak.justfit.data.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.justfit.common.data.NetworkBoundResource
import pl.kamilszustak.justfit.common.data.NetworkCall
import pl.kamilszustak.justfit.common.data.Resource
import pl.kamilszustak.justfit.data.database.dao.EventDao
import pl.kamilszustak.justfit.domain.mapper.EventJsonMapper
import pl.kamilszustak.justfit.domain.model.event.Event
import pl.kamilszustak.justfit.domain.model.event.EventJson
import pl.kamilszustak.justfit.network.model.JoinLeaveEventRequestBody
import pl.kamilszustak.justfit.network.service.EventApiService
import pl.kamilszustak.justfit.util.withIOContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventRepository @Inject constructor(
    private val eventDao: EventDao,
    private val eventApiService: EventApiService,
    private val userDetailsRepository: UserDetailsRepository,
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

    fun getById(id: Long, shouldFetch: Boolean = true): Flow<Resource<Event>> {
        return object : NetworkBoundResource<EventJson, Event>() {
            override fun loadFromDatabase(): Flow<Event> =
                eventDao.getById(id)

            override fun shouldFetch(data: Event?): Boolean = shouldFetch

            override suspend fun fetchFromNetwork(): Response<EventJson> =
                eventApiService.getById(id)

            override suspend fun saveFetchResult(result: EventJson) {
                eventJsonMapper.onMap(result) { event ->
                    eventDao.insert(event)
                }
            }
        }.asFlow()
    }

    suspend fun join(eventId: Long): Result<Unit> {
        val requestBody = JoinLeaveEventRequestBody(eventId)

        return withIOContext {
            object : NetworkCall<EventJson, Unit>() {
                override suspend fun makeCall(): Response<EventJson> =
                    eventApiService.join(
                        clientId = clientId,
                        requestBody = requestBody
                    )

                override suspend fun mapResponse(response: EventJson) = Unit

                override suspend fun saveCallResult(result: EventJson) {
                    eventJsonMapper.onMap(result) { event ->
                        eventDao.insert(event)
                    }
                }
            }.callForResponse()
        }
    }

    suspend fun leave(eventId: Long): Result<Unit> {
        val requestBody = JoinLeaveEventRequestBody(eventId)

        return withIOContext {
            object : NetworkCall<EventJson, Unit>() {
                override suspend fun makeCall(): Response<EventJson> =
                    eventApiService.leave(
                        clientId = clientId,
                        requestBody = requestBody
                    )

                override suspend fun mapResponse(response: EventJson) = Unit

                override suspend fun saveCallResult(result: EventJson) {
                    eventJsonMapper.onMap(result) { event ->
                        eventDao.insert(event)
                    }
                }
            }.callForResponse()
        }
    }

    private val clientId: Long
        get() = userDetailsRepository.getValue(UserDetailsRepository.UserDetailsKey.USER_ID)
}