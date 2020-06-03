package pl.kamilszustak.justfit.domain.mapper.product

import pl.kamilszustak.justfit.domain.mapper.JsonModelMapper
import pl.kamilszustak.justfit.domain.model.product.ClientProductEntity
import pl.kamilszustak.justfit.network.model.ClientProductJson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClientProductJsonMapper @Inject constructor() : JsonModelMapper<ClientProductJson, ClientProductEntity>() {
    override fun map(model: ClientProductJson): ClientProductEntity =
        ClientProductEntity(
            userId = model.userId,
            productId = model.productId
        ).apply {
            this.id = model.id
        }
}