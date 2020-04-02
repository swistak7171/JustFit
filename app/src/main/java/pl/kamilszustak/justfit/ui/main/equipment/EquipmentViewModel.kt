package pl.kamilszustak.justfit.ui.main.equipment

import android.app.Application
import androidx.annotation.IdRes
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.common.livedata.ResourceDataSource
import pl.kamilszustak.justfit.domain.model.equipment.Equipment
import pl.kamilszustak.justfit.domain.usecase.equipment.GetAllAvailableEquipmentUseCase
import pl.kamilszustak.justfit.domain.usecase.equipment.GetAllEquipmentUseCase
import pl.kamilszustak.justfit.ui.base.BaseViewModel
import javax.inject.Inject

class EquipmentViewModel @Inject constructor(
    application: Application,
    private val getAllEquipmentUseCase: GetAllEquipmentUseCase,
    private val getAllAvailableEquipmentUseCase: GetAllAvailableEquipmentUseCase
) : BaseViewModel(application) {

    val equipmentResource: ResourceDataSource<List<Equipment>> = ResourceDataSource()

    fun loadData(@IdRes checkedButtonId: Int) {
        val equipmentFlow = when (checkedButtonId) {
            R.id.allEquipmentButton -> getAllEquipmentUseCase()
            R.id.allAvailableEquipmentButton -> getAllAvailableEquipmentUseCase()
            else -> throw IllegalStateException("Unknown equipment filter")
        }

        equipmentResource.changeFlowSource {
            equipmentFlow
        }
    }

    fun onRefresh() {
        equipmentResource.refresh()
    }
}