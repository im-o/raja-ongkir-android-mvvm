package com.stimednp.mvvmrajaongkir.network

import com.stimednp.mvvmrajaongkir.model.CityResponse
import com.stimednp.mvvmrajaongkir.model.CostResponse
import com.stimednp.mvvmrajaongkir.model.ProvinceResponse
import retrofit2.http.*

/**
 * Created by rivaldy on Nov/14/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

interface ApiRajaOngkir {

    @GET("province")
    suspend fun getProvinces(
        @Query("key") apiKey: String?
    ) : ProvinceResponse

    @GET("city")
    suspend fun getCities(
        @Query("key") apiKey: String?
    ) : CityResponse

    @FormUrlEncoded
    @POST("cost")
    suspend fun getCost(
        @Header("key") apiKey: String?,
        @Field("origin") origin: String?,
        @Field("destination") destination: String?,
        @Field("weight") weight: Int?,
        @Field("courier") courier: String?
    ) : CostResponse
}