package com.stimednp.mvvmrajaongkir.data.model.cost


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CostResult(
    @SerializedName("code")
    val code: String? = null,
    @SerializedName("costs")
    val costs: List<Costs>? = null,
    @SerializedName("name")
    val name: String? = null
) : Parcelable