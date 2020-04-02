package pl.kamilszustak.justfit.domain.model.equipment

import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize
import pl.kamilszustak.justfit.domain.model.DatabaseEntity

@Entity(tableName = "equipment")
@Parcelize
data class Equipment(
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "specification")
    val specification: String,

    @ColumnInfo(name = "is_available")
    val isAvailable: Boolean
) : DatabaseEntity()