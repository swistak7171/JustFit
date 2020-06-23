package pl.kamilszustak.justfit.domain.mapper.product

import pl.kamilszustak.justfit.domain.mapper.Mapper
import pl.kamilszustak.justfit.domain.model.product.Product
import pl.kamilszustak.justfit.domain.model.product.ProductEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductEntityMapper @Inject constructor() : Mapper<ProductEntity, Product>() {
    override fun map(model: ProductEntity): Product =
        Product(
            id = model.id,
            name = model.name,
            description = model.description,
            price = model.price
        )
}