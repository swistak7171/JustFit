package pl.kamilszustak.justfit.domain.usecase.equipment

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.justfit.common.data.Resource
import pl.kamilszustak.justfit.data.repository.EquipmentRepository
import pl.kamilszustak.justfit.domain.model.equipment.Equipment
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllEquipmentUseCaseImpl @Inject constructor(
    private val equipmentRepository: EquipmentRepository
) : GetAllEquipmentUseCase {

    override fun invoke(): Flow<Resource<List<Equipment>>> =
        equipmentRepository.getAll()
}