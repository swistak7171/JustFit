package pl.kamilszustak.justfit.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.justfit.domain.model.product.ProductEntity

@Dao
interface ProductDao : BaseDao<ProductEntity> {
    @Query("SELECT * FROM products")
    fun getAll(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE id = :id")
    fun getById(id: Long): Flow<ProductEntity>

    @Transaction
    suspend fun replaceAll(products: Collection<ProductEntity>) {
        deleteAll()
        insertAll(products)
    }

    @Query("DELETE FROM products")
    suspend fun deleteAll()
}