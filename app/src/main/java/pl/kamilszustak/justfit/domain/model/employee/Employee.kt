package pl.kamilszustak.justfit.domain.model.employee

import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize
import pl.kamilszustak.justfit.domain.model.DatabaseEntity

@Entity(tableName = "employees")
@Parcelize
data class Employee(
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "surname")
    val surname: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "phone_number")
    val phoneNumber: String,

    @ColumnInfo(name = "specialization")
    val specialization: String,

    @ColumnInfo(name = "work_hours")
    val workHours: String
) : DatabaseEntity() {

    val fullName: String
        get() = "$name $surname"
}