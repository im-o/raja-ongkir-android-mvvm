package com.stimednp.mvvmrajaongkir.data.model.cost


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CostQuery(
    @SerializedName("courier")
    val courier: String? = null,
    @SerializedName("destination")
    val destination: String? = null,
    @SerializedName("origin")
    val origin: String? = null,
    @SerializedName("weight")
    val weight: Int? = null
) : Parcelable