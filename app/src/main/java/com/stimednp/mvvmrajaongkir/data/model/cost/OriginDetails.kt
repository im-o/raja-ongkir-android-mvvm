package com.stimednp.mvvmrajaongkir.data.model.cost


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OriginDetails(
    @SerializedName("city_id")
    val cityId: String? = null,
    @SerializedName("city_name")
    val cityName: String? = null,
    @SerializedName("postal_code")
    val postalCode: String? = null,
    @SerializedName("province")
    val province: String? = null,
    @SerializedName("province_id")
    val provinceId: String? = null,
    @SerializedName("type")
    val type: String? = null
) : Parcelable