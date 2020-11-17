package com.stimednp.mvvmrajaongkir.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class CostResponse(
    @SerializedName("rajaongkir")
    val rajaOngkir: RajaOngkir? = null
) {

    data class RajaOngkir(
        @SerializedName("destination_details")
        val destinationDetails: DestinationDetails? = null,
        @SerializedName("origin_details")
        val originDetails: OriginDetails? = null,
        @SerializedName("query")
        val query: Query? = null,
        @SerializedName("results")
        val results: List<Results?>? = null,
        @SerializedName("status")
        val status: Status? = null
    ) {

        @Parcelize
        data class DestinationDetails(
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

        data class Query(
            @SerializedName("courier")
            val courier: String? = null,
            @SerializedName("destination")
            val destination: String? = null,
            @SerializedName("origin")
            val origin: String? = null,
            @SerializedName("weight")
            val weight: Int? = null
        )

        data class Results(
            @SerializedName("code")
            val code: String? = null,
            @SerializedName("costs")
            val costs: List<Costs?>? = null,
            @SerializedName("name")
            val name: String? = null
        ) {

            data class Costs(
                @SerializedName("cost")
                val cost: List<Cost?>? = null,
                @SerializedName("description")
                val description: String? = null,
                @SerializedName("service")
                val service: String? = null
            ) {

                data class Cost(
                    @SerializedName("etd")
                    val etd: String? = null,
                    @SerializedName("note")
                    val note: String? = null,
                    @SerializedName("value")
                    val value: Int? = null
                )
            }
        }

        data class Status(
            @SerializedName("code")
            val code: Int? = null,
            @SerializedName("description")
            val description: String? = null
        )
    }
}