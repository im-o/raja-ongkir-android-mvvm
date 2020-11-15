package com.stimednp.mvvmrajaongkir.model


import com.google.gson.annotations.SerializedName

data class CityResponse(
    @SerializedName("rajaongkir")
    val rajaOngkir: RajaOngkir? = null
) {

    data class RajaOngkir(
        @SerializedName("query")
        val query: Query? = null,
        @SerializedName("results")
        val results: List<Result?>? = null,
        @SerializedName("status")
        val status: Status? = null
    ) {

        data class Query(
            @SerializedName("key")
            val key: String? = null
        )

        data class Result(
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
        )

        data class Status(
            @SerializedName("code")
            val code: Int? = null,
            @SerializedName("description")
            val description: String? = null
        )
    }
}