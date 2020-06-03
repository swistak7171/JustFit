package pl.kamilszustak.justfit.domain.model.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize
import pl.kamilszustak.justfit.domain.model.DatabaseEntity

@Entity(tableName = "products")
@Parcelize
data class ProductEntity(
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "price")
    val price: Double
) : DatabaseEntity()