package pl.kamilszustak.justfit.domain.usecase.product

import pl.kamilszustak.justfit.data.repository.ProductRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BuyProductUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository
) : BuyProductUseCase {

    override suspend fun invoke(productId: Long): Result<Unit> =
        productRepository.buyById(productId)
}