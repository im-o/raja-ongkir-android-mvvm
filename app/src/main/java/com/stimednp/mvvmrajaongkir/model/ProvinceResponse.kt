package com.stimednp.mvvmrajaongkir.model


import com.google.gson.annotations.SerializedName

data class ProvinceResponse(
    @SerializedName("rajaongkir")
    val rajaOngkir: RajaOngkir? = null
) {

    data class RajaOngkir(
        @SerializedName("query")
        val query: Query? = null,
        @SerializedName("results")
        val results: List<Results?>? = null,
        @SerializedName("status")
        val status: Status? = null
    ) {

        data class Query(
            @SerializedName("key")
            val key: String? = null
        )

        data class Results(
            @SerializedName("province")
            val province: String? = null,
            @SerializedName("province_id")
            val provinceId: String? = null
        )

        data class Status(
            @SerializedName("code")
            val code: Int? = null,
            @SerializedName("description")
            val description: String? = null
        )
    }
}