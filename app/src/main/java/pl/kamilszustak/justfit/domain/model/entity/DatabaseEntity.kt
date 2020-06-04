package pl.kamilszustak.justfit.domain.model.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

abstract class DatabaseEntity : Entity(), Parcelable {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Long = 0
}