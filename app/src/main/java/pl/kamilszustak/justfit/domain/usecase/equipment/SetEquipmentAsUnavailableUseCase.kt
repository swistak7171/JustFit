package pl.kamilszustak.justfit.domain.usecase.equipment

interface SetEquipmentAsUnavailableUseCase {
    suspend operator fun invoke(id: Long): Result<Unit>
}