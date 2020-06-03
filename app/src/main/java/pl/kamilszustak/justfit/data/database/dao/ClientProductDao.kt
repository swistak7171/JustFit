package pl.kamilszustak.justfit.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.justfit.domain.model.product.ClientProductEntity

@Dao
interface ClientProductDao : BaseDao<ClientProductEntity> {
    @Query("SELECT * FROM clients_products")
    fun getAll(): Flow<List<ClientProductEntity>>

    @Transaction
    suspend fun replaceAll(products: Collection<ClientProductEntity>) {
        deleteAll()
        insertAll(products)
    }

    @Query("DELETE FROM clients_products")
    suspend fun deleteAll()
}