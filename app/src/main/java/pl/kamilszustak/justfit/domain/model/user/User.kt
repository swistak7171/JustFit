package pl.kamilszustak.justfit.domain.model.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize
import pl.kamilszustak.justfit.domain.model.entity.DatabaseEntity

@Entity(tableName = "users")
@Parcelize
data class User(
    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "surname")
    val surname: String,

    @ColumnInfo(name = "phone_number")
    val phoneNumber: String
) : DatabaseEntity() {

    val fullName: String
        get() = "$name $surname"
}