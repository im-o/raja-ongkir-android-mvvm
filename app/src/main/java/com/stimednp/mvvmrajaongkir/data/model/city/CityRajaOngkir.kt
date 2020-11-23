package com.stimednp.mvvmrajaongkir.data.model.city


import com.google.gson.annotations.SerializedName

data class CityRajaOngkir(
    @SerializedName("query")
    val query: CityQuery? = null,
    @SerializedName("results")
    val results: List<CityResult>? = null,
    @SerializedName("status")
    val status: CityStatus? = null
)