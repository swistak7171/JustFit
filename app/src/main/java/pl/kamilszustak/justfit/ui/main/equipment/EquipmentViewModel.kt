package pl.kamilszustak.justfit.ui.main.equipment

import android.app.Application
import androidx.annotation.IdRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.common.livedata.ResourceDataSource
import pl.kamilszustak.justfit.common.livedata.SingleLiveData
import pl.kamilszustak.justfit.common.livedata.UniqueLiveData
import pl.kamilszustak.justfit.domain.model.equipment.Equipment
import pl.kamilszustak.justfit.domain.usecase.equipment.GetAllAvailableEquipmentUseCase
import pl.kamilszustak.justfit.domain.usecase.equipment.GetAllEquipmentUseCase
import pl.kamilszustak.justfit.domain.usecase.equipment.SetEquipmentAsUnavailableUseCase
import pl.kamilszustak.justfit.ui.base.BaseViewModel
import javax.inject.Inject

class EquipmentViewModel @Inject constructor(
    application: Application,
    private val getAllEquipmentUseCase: GetAllEquipmentUseCase,
    private val getAllAvailableEquipmentUseCase: GetAllAvailableEquipmentUseCase,
    private val setEquipmentAsUnavailableUseCase: SetEquipmentAsUnavailableUseCase
) : BaseViewModel(application) {

    val equipmentResource: ResourceDataSource<List<Equipment>> = ResourceDataSource()

    private val _checkedButtonId: UniqueLiveData<Int> = UniqueLiveData(R.id.allEquipmentButton)
    val checkedButtonId: LiveData<Int> = _checkedButtonId

    private val _isLoading: UniqueLiveData<Boolean> = UniqueLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error: SingleLiveData<Int> = SingleLiveData()
    val error: LiveData<Int> = _error

    init {
        equipmentResource.result.addSource(_checkedButtonId) { id ->
            if (id == null) {
                return@addSource
            }

            val equipmentFlow = when (id) {
                R.id.allEquipmentButton -> getAllEquipmentUseCase()
                R.id.allAvailableEquipmentButton -> getAllAvailableEquipmentUseCase()
                else -> throw IllegalStateException("Unknown equipment filter")
            }

            equipmentResource.setFlowSource {
                equipmentFlow
            }
        }
    }

    fun onSetAsUnavailableButtonClick(equipmentId: Long) {
        viewModelScope.launch(Dispatchers.Main) {
            _isLoading.value = true

            val result = setEquipmentAsUnavailableUseCase(equipmentId)
            result.onFailure {
                _error.value = R.string.set_equipment_as_unavailable_error_message
            }

            _isLoading.value = false
        }
    }

    fun onCheckedButtonChange(@IdRes buttonId: Int) {
        _checkedButtonId.value = buttonId
    }

    fun onRefresh() {
        equipmentResource.refresh()
    }
}