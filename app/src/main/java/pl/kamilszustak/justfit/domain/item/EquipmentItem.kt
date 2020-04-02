package pl.kamilszustak.justfit.domain.item

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.databinding.ItemEquipmentListBinding
import pl.kamilszustak.justfit.domain.model.equipment.Equipment

class EquipmentItem(equipment: Equipment) : ModelAbstractBindingItem<Equipment, ItemEquipmentListBinding>(equipment) {

    override var identifier: Long
        get() = this.model.id
        set(value) {}

    override val type: Int = R.id.fastadapter_equipment_item

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemEquipmentListBinding =
        ItemEquipmentListBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ItemEquipmentListBinding, payloads: List<Any>) {
        with(binding) {

        }
    }

    override fun unbindView(binding: ItemEquipmentListBinding) {
        with(binding) {

        }
    }
}