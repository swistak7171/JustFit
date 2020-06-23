package pl.kamilszustak.justfit.domain.usecase.activity

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.justfit.common.data.Resource
import pl.kamilszustak.justfit.domain.model.activity.Activity

interface GetAllActivitiesUseCase {
    operator fun invoke(shouldFetch: Boolean = true): Flow<Resource<List<Activity>>>
}