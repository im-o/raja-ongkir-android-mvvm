package com.stimednp.mvvmrajaongkir.data.model.cost


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CostStatus(
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("description")
    val description: String? = null
) : Parcelable