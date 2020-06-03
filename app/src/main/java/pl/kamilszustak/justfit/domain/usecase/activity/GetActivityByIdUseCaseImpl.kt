package pl.kamilszustak.justfit.domain.usecase.activity

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.kamilszustak.justfit.common.data.Resource
import pl.kamilszustak.justfit.data.repository.ActivityRepository
import pl.kamilszustak.justfit.domain.mapper.activity.ActivityWithEquipmentMapper
import pl.kamilszustak.justfit.domain.model.activity.Activity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetActivityByIdUseCaseImpl @Inject constructor(
    private val activityRepository: ActivityRepository,
    private val activityWithEquipmentMapper: ActivityWithEquipmentMapper
) : GetActivityByIdUseCase {

    override fun invoke(id: Long, shouldFetch: Boolean): Flow<Resource<Activity>> =
        activityRepository.getById(id, shouldFetch)
            .map { resource ->
                resource.mapData { activityWithEquipment ->
                    activityWithEquipmentMapper.map(activityWithEquipment)
                }
            }
}