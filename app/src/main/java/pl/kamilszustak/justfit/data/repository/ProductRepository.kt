package pl.kamilszustak.justfit.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.kamilszustak.justfit.common.data.NetworkBoundResource
import pl.kamilszustak.justfit.common.data.NetworkCall
import pl.kamilszustak.justfit.common.data.Resource
import pl.kamilszustak.justfit.data.database.dao.ClientProductDao
import pl.kamilszustak.justfit.data.database.dao.ProductDao
import pl.kamilszustak.justfit.domain.mapper.product.ClientProductJsonMapper
import pl.kamilszustak.justfit.domain.mapper.product.ProductJsonMapper
import pl.kamilszustak.justfit.domain.model.product.ProductEntity
import pl.kamilszustak.justfit.domain.model.product.ProductJson
import pl.kamilszustak.justfit.network.model.BuyProductRequestBody
import pl.kamilszustak.justfit.network.model.ClientProductJson
import pl.kamilszustak.justfit.network.service.ClientApiService
import pl.kamilszustak.justfit.network.service.ProductApiService
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val productDao: ProductDao,
    private val clientProductDao: ClientProductDao,
    private val productApiService: ProductApiService,
    private val clientApiService: ClientApiService,
    private val userDetailsRepository: UserDetailsRepository,
    private val productJsonMapper: ProductJsonMapper,
    private val clientProductJsonMapper: ClientProductJsonMapper
) {
    fun getAll(shouldFetch: Boolean): Flow<Resource<List<ProductEntity>>> {
        return object : NetworkBoundResource<List<ProductJson>, List<ProductEntity>>() {
            override fun loadFromDatabase(): Flow<List<ProductEntity>> =
                productDao.getAll()

            override fun shouldFetch(data: List<ProductEntity>?): Boolean = shouldFetch

            override suspend fun fetchFromNetwork(): Response<List<ProductJson>> =
                productApiService.getAll()

            override suspend fun saveFetchResult(result: List<ProductJson>) {
                val products = productJsonMapper.mapElementsIf(result, ProductJson::isActive)
                productDao.replaceAll(products)
            }
        }.asFlow()
    }

    fun getById(id: Long, shouldFetch: Boolean): Flow<Resource<ProductEntity>> {
        return object : NetworkBoundResource<ProductJson, ProductEntity>() {
            override fun loadFromDatabase(): Flow<ProductEntity> =
                productDao.getById(id)

            override fun shouldFetch(data: ProductEntity?): Boolean = shouldFetch

            override suspend fun fetchFromNetwork(): Response<ProductJson> =
                productApiService.getById(id)

            override suspend fun saveFetchResult(result: ProductJson) {
                val product = productJsonMapper.mapIf(result, ProductJson::isActive)
                if (product != null) {
                    productDao.insert(product)
                }
            }
        }.asFlow()
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun getAllBoughtByClient(shouldFetch: Boolean): Flow<Resource<List<ProductEntity>>> {
        return flow {
            emit(Resource.loading(null))
            val response = clientApiService.getAllClientProducts()
            val body = response.body()
            if (!response.isSuccessful || body == null) {
                emit(Resource.error("Unsuccessful API request", null))
                return@flow
            }

            val productsIds = body.map(ClientProductJson::productId)
            val products = buildList<ProductEntity> {
                productsIds.forEach { id ->
                    val alreadyFetchedProduct = this.find { it.id == id }
                    if (alreadyFetchedProduct != null) {
                        this.add(alreadyFetchedProduct)
                        return@forEach
                    }

                    val response = productApiService.getById(id)
                    val body = response.body()
                    if (!response.isSuccessful || body == null) {
                        emit(Resource.error("Unsuccessful API request", null))
                        return@flow
                    }

                    productJsonMapper.onMap(body) { product ->
                        this.add(product)
                    }
                }
            }

            emit(Resource.success(products))
        }
    }

    suspend fun buyById(id: Long): Result<Unit> {
        val requestBody = BuyProductRequestBody(
            userId = getUserId(),
            productId = id
        )

        return object : NetworkCall<ClientProductJson, Unit>() {
            override suspend fun makeCall(): Response<ClientProductJson> =
                clientApiService.buyProduct(requestBody)

            override suspend fun mapResponse(response: ClientProductJson) = Unit

            override suspend fun saveCallResult(result: ClientProductJson) {
                super.saveCallResult(result)
            }
        }.callForResponse()
    }

    private fun getUserId(): Long =
        userDetailsRepository.getValue(UserDetailsRepository.UserDetailsKey.USER_ID)
}