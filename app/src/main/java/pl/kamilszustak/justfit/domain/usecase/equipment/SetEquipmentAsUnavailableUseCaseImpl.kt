package pl.kamilszustak.justfit.domain.usecase.equipment

import pl.kamilszustak.justfit.data.repository.EquipmentRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SetEquipmentAsUnavailableUseCaseImpl @Inject constructor(
    private val equipmentRepository: EquipmentRepository
) : SetEquipmentAsUnavailableUseCase {

    override suspend fun invoke(id: Long): Result<Unit> =
        equipmentRepository.updateAvailabilityById(id, false)
}