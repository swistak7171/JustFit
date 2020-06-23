package pl.kamilszustak.justfit.domain.usecase.equipment

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.justfit.common.data.Resource
import pl.kamilszustak.justfit.domain.model.equipment.Equipment

interface GetAllEquipmentUseCase {
    operator fun invoke(): Flow<Resource<List<Equipment>>>
}