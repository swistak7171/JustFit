package pl.kamilszustak.justfit.ui.main.product.client

import android.app.Application
import pl.kamilszustak.justfit.common.livedata.ResourceDataSource
import pl.kamilszustak.justfit.domain.model.product.Product
import pl.kamilszustak.justfit.domain.usecase.product.GetAllClientProductsUseCase
import pl.kamilszustak.justfit.ui.base.BaseViewModel
import javax.inject.Inject

class ClientProductsViewModel @Inject constructor(
    application: Application,
    private val getAllClientProducts: GetAllClientProductsUseCase
) : BaseViewModel(application) {

    val productsResource: ResourceDataSource<List<Product>> = ResourceDataSource()

    init {
        productsResource.setFlowSource {
            getAllClientProducts()
        }
    }

    fun onRefresh() {
        productsResource.refresh()
    }
}