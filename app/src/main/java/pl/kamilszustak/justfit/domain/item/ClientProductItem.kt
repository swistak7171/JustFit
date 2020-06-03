package pl.kamilszustak.justfit.domain.item

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.databinding.ItemClientProductsListBinding
import pl.kamilszustak.justfit.domain.model.product.Product

class ClientProductItem(product: Product) : ModelAbstractBindingItem<Product, ItemClientProductsListBinding>(product) {
    override var identifier: Long
        get() = this.model.id
        set(value) {}

    override val type: Int = R.id.fastadapter_client_product_item

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemClientProductsListBinding =
        ItemClientProductsListBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ItemClientProductsListBinding, payloads: List<Any>) {
        binding.product = this.model
    }

    override fun unbindView(binding: ItemClientProductsListBinding) {
        with(binding) {
            this.product = null
            this.executePendingBindings()
        }
    }
}