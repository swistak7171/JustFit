package pl.kamilszustak.justfit.data.repository

import pl.kamilszustak.justfit.data.database.dao.EquipmentDao
import pl.kamilszustak.justfit.network.service.EquipmentApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EquipmentRepository @Inject constructor(
    private val equipmentDao: EquipmentDao,
    private val equipmentApiService: EquipmentApiService
) {
}