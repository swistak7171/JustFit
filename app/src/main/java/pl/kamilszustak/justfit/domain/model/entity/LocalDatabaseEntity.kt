package pl.kamilszustak.justfit.domain.model.entity

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

abstract class LocalDatabaseEntity : Entity() {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}