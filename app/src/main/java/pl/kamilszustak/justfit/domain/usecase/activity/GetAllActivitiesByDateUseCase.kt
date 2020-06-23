package pl.kamilszustak.justfit.domain.usecase.activity

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.justfit.common.data.Resource
import pl.kamilszustak.justfit.domain.model.activity.Activity
import java.util.Date

interface GetAllActivitiesByDateUseCase {
    operator fun invoke(date: Date, shouldFetch: Boolean = true): Flow<Resource<List<Activity>>>
}