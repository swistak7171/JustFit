package pl.kamilszustak.justfit.ui.main.product

import android.app.Application
import pl.kamilszustak.justfit.common.livedata.ResourceDataSource
import pl.kamilszustak.justfit.domain.model.product.Product
import pl.kamilszustak.justfit.domain.usecase.product.GetAllProductsUseCase
import pl.kamilszustak.justfit.ui.base.BaseViewModel
import javax.inject.Inject

class ProductsViewModel @Inject constructor(
    application: Application,
    private val getAllProducts: GetAllProductsUseCase
) : BaseViewModel(application) {

    val productsResource: ResourceDataSource<List<Product>> = ResourceDataSource()

    init {
        productsResource.setFlowSource {
            getAllProducts()
        }
    }

    fun onRefresh() {
        productsResource.refresh()
    }
}