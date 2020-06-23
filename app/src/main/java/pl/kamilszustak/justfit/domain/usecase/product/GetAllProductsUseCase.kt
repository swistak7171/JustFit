package pl.kamilszustak.justfit.domain.usecase.product

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.justfit.common.data.Resource
import pl.kamilszustak.justfit.domain.model.product.Product

interface GetAllProductsUseCase {
    operator fun invoke(shouldFetch: Boolean = true): Flow<Resource<List<Product>>>
}