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
class GetAllClientActivitiesUseCaseImpl @Inject constructor(
    private val activityRepository: ActivityRepository,
    private val activityWithEquipmentMapper: ActivityWithEquipmentMapper
) : GetAllClientActivitiesUseCase {

    override fun invoke(): Flow<Resource<List<Activity>>> =
        activityRepository.getAllJoinedInByClient()
            .map { resource ->
                resource.mapData { activities ->
                    activityWithEquipmentMapper.mapAll(activities)
                }
            }
}