package com.stimednp.mvvmrajaongkir.data.model.cost


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cost(
    @SerializedName("etd")
    val etd: String? = null,
    @SerializedName("note")
    val note: String? = null,
    @SerializedName("value")
    val value: Int? = null
) : Parcelable