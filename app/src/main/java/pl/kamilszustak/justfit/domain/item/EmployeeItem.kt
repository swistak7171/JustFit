package pl.kamilszustak.justfit.domain.item

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.databinding.ItemEmployeesListBinding
import pl.kamilszustak.justfit.domain.model.employee.Employee

class EmployeeItem(employee: Employee) : ModelAbstractBindingItem<Employee, ItemEmployeesListBinding>(employee) {
    override var identifier: Long
        get() = this.model.id
        set(value) {}

    override val type: Int = R.id.fastadapter_employee_item

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemEmployeesListBinding =
        ItemEmployeesListBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ItemEmployeesListBinding, payloads: List<Any>) {
        with(binding) {
            nameTextView.text = model.fullName
            specializationTextView.text = model.specialization
        }
    }

    override fun unbindView(binding: ItemEmployeesListBinding) {
        with(binding) {
            nameTextView.text = null
            specializationTextView.text = null
        }
    }
}