package pl.kamilszustak.justfit.domain.model.product

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val id: Long,
    val name: String,
    val description: String,
    val price: Double
) : Parcelable