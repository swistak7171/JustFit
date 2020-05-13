package pl.kamilszustak.justfit.domain.usecase.product

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.kamilszustak.justfit.common.data.Resource
import pl.kamilszustak.justfit.data.repository.ProductRepository
import pl.kamilszustak.justfit.domain.mapper.product.ProductEntityMapper
import pl.kamilszustak.justfit.domain.model.product.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetProductByIdUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository,
    private val productEntityMapper: ProductEntityMapper
) : GetProductByIdUseCase {

    override fun invoke(id: Long, shouldFetch: Boolean): Flow<Resource<Product>> =
        productRepository.getById(id, shouldFetch)
            .map { resource ->
                resource.mapData { product ->
                    productEntityMapper.map(product)
                }
            }
}