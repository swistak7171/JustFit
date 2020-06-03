package pl.kamilszustak.justfit.domain.item

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.databinding.ItemProductsListBinding
import pl.kamilszustak.justfit.domain.model.product.Product

open class ProductItem(product: Product) : ModelAbstractBindingItem<Product, ItemProductsListBinding>(product) {
    override var identifier: Long
        get() = this.model.id
        set(value) {}

    override val type: Int = R.id.fastadapter_product_item

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemProductsListBinding =
        ItemProductsListBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ItemProductsListBinding, payloads: List<Any>) {
        binding.product = this.model
    }

    override fun unbindView(binding: ItemProductsListBinding) {
        with(binding) {
            this.product = null
            this.executePendingBindings()
        }
    }
}