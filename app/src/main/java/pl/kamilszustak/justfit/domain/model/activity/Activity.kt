package pl.kamilszustak.justfit.domain.model.activity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import pl.kamilszustak.justfit.domain.model.equipment.Equipment
import java.util.Date

@Parcelize
data class Activity(
    val id: Long,
    val name: String,
    val leaderName: String,
    val type: String,
    val startDate: Date,
    val endDate: Date,
    val usedEquipment: List<Equipment>,
    val isCancelled: Boolean
) : Parcelable