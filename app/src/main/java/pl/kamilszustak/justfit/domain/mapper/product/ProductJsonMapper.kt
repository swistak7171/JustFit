package pl.kamilszustak.justfit.domain.mapper.product

import pl.kamilszustak.justfit.domain.mapper.JsonModelMapper
import pl.kamilszustak.justfit.domain.model.product.ProductEntity
import pl.kamilszustak.justfit.domain.model.product.ProductJson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductJsonMapper @Inject constructor() : JsonModelMapper<ProductJson, ProductEntity>() {
    override fun map(model: ProductJson): ProductEntity =
        ProductEntity(
            name = model.name,
            description = model.description,
            price = model.price
        ).apply {
            this.id = model.id
        }
}