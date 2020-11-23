package com.stimednp.mvvmrajaongkir.data.model.cost


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CostRajaOngkir(
    @SerializedName("destination_details")
    val destinationDetails: DestinationDetails? = null,
    @SerializedName("origin_details")
    val originDetails: OriginDetails? = null,
    @SerializedName("query")
    val query: CostQuery? = null,
    @SerializedName("results")
    val results: List<CostResult>? = null,
    @SerializedName("status")
    val status: CostStatus? = null
) : Parcelable