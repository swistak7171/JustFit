package pl.kamilszustak.justfit.domain.item

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem
import org.jetbrains.anko.textColorResource
import org.jetbrains.anko.textResource
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
            nameTextView.text = model.name
            specificationTextView.text = model.specification
            availabilityTextView.apply {
                if (model.isAvailable) {
                    this.textResource = R.string.equipment_is_available
                    this.textColorResource = android.R.color.holo_green_light
                } else {
                    this.textResource = R.string.equipment_is_not_availble
                    this.textColorResource = android.R.color.holo_red_light
                }
            }
        }
    }

    override fun unbindView(binding: ItemEquipmentListBinding) {
        with(binding) {
            nameTextView.text = null
            specificationTextView.text = null
            availabilityTextView.apply {
                this.text = null
                this.textColorResource = android.R.color.black
            }
        }
    }
}