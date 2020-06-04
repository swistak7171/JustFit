package pl.kamilszustak.justfit.domain.item

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.databinding.ItemActivityEquipmentListBinding
import pl.kamilszustak.justfit.domain.model.equipment.Equipment
import timber.log.Timber

class ActivityEquipmentItem(equipment: Equipment) : ModelAbstractBindingItem<Equipment, ItemActivityEquipmentListBinding>(equipment) {
    override var identifier: Long
        get() = this.model.id
        set(value) {}

    override val type: Int = R.id.fastadapter_activity_equipment_item

    init {
        Timber.i(equipment.toString())
    }

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemActivityEquipmentListBinding =
        ItemActivityEquipmentListBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ItemActivityEquipmentListBinding, payloads: List<Any>) {
        binding.equipment = this.model
    }

    override fun unbindView(binding: ItemActivityEquipmentListBinding) {
        with(binding) {
            this.equipment = null
            this.executePendingBindings()
        }
    }
}