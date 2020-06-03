package pl.kamilszustak.justfit.ui.main.product

import android.app.Application
import androidx.lifecycle.LiveData
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.common.livedata.ResourceDataSource
import pl.kamilszustak.justfit.common.livedata.SingleLiveData
import pl.kamilszustak.justfit.domain.model.product.Product
import pl.kamilszustak.justfit.domain.usecase.product.BuyProductUseCase
import pl.kamilszustak.justfit.domain.usecase.product.GetAllProductsUseCase
import pl.kamilszustak.justfit.ui.base.StateViewModel
import javax.inject.Inject

class ProductsViewModel @Inject constructor(
    application: Application,
    private val getAllProducts: GetAllProductsUseCase,
    private val buyProductUseCase: BuyProductUseCase
) : StateViewModel(application) {

    val productsResource: ResourceDataSource<List<Product>> = ResourceDataSource()

    private val _showBuyDialog: SingleLiveData<Product> = SingleLiveData()
    val showBuyDialog: LiveData<Product> = _showBuyDialog

    init {
        productsResource.setFlowSource {
            getAllProducts()
        }
    }

    fun onRefresh() {
        productsResource.refresh()
    }

    fun onProductClicked(productId: Long) {
        val product = productsResource.data.value?.find {
            it.id == productId
        } ?: return

        _showBuyDialog.value = product
    }

    fun onBuyConfirmationClicked(productId: Long) {
        performAction(R.string.buying_product_error_message) {
            buyProductUseCase(productId)
        }
    }
}