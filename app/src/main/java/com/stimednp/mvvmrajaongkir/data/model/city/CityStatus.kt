package com.stimednp.mvvmrajaongkir.data.model.city


import com.google.gson.annotations.SerializedName

data class CityStatus(
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("description")
    val description: String? = null
)