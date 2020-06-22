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
class GetAllClientProductsUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository,
    private val productEntityMapper: ProductEntityMapper
) : GetAllClientProductsUseCase {

    override fun invoke(): Flow<Resource<List<Product>>> =
        productRepository.getAllBoughtByClient()
            .map { resource ->
                resource.mapData { products ->
                    products.distinct()
                        .let {
                            productEntityMapper.mapAll(it)
                        }
                }
            }
}