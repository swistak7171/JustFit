package pl.kamilszustak.justfit.domain.usecase.product

interface BuyProductUseCase {
    suspend operator fun invoke(productId: Long): Result<Unit>
}