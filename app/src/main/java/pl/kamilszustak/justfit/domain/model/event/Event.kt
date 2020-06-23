package pl.kamilszustak.justfit.domain.model.event

import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize
import pl.kamilszustak.justfit.domain.model.entity.DatabaseEntity
import java.util.Date

@Entity(tableName = "events")
@Parcelize
data class Event(
    @ColumnInfo(name = "employee_id")
    val employeeId: Long,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "start_date")
    val startDate: Date,

    @ColumnInfo(name = "end_date")
    val endDate: Date,

    @ColumnInfo(name = "number_of_attendees")
    val numberOfAttendees: Int
) : DatabaseEntity()