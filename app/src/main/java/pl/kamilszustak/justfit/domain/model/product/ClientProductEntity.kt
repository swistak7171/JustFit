package pl.kamilszustak.justfit.domain.model.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize
import pl.kamilszustak.justfit.domain.model.DatabaseEntity

@Entity(tableName = "clients_products")
@Parcelize
data class ClientProductEntity(
    @ColumnInfo(name = "user_id")
    val userId: Long,

    @ColumnInfo(name = "product_id")
    val productId: Long
) : DatabaseEntity()