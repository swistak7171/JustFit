package pl.kamilszustak.justfit.domain.usecase.product

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.justfit.common.data.Resource
import pl.kamilszustak.justfit.domain.model.product.Product

interface GetProductByIdUseCase {
    operator fun invoke(id: Long, shouldFetch: Boolean = true): Flow<Resource<Product>>
}