package com.stimednp.mvvmrajaongkir.data.model.cost


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Costs(
    @SerializedName("cost")
    val cost: List<Cost>? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("service")
    val service: String? = null
) : Parcelable