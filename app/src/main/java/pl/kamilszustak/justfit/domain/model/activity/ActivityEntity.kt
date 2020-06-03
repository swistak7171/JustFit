package pl.kamilszustak.justfit.domain.model.activity

import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize
import pl.kamilszustak.justfit.domain.model.DatabaseEntity
import java.util.Date

@Entity(tableName = "activities")
@Parcelize
data class ActivityEntity(
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "leader_name")
    val leaderName: String,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "start_date")
    val startDate: Date,

    @ColumnInfo(name = "end_date")
    val endDate: Date,

    @ColumnInfo(name = "is_cancelled")
    val isCancelled: Boolean
) : DatabaseEntity()