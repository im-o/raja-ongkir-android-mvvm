package com.stimednp.mvvmrajaongkir.data.repositories

import com.stimednp.mvvmrajaongkir.APIKey.Companion.API_KEY
import com.stimednp.mvvmrajaongkir.data.model.city.CityResponse
import com.stimednp.mvvmrajaongkir.data.model.cost.CostResponse
import com.stimednp.mvvmrajaongkir.data.network.ApiRajaOngkir
import javax.inject.Inject

/**
 * Created by rivaldy on Nov/15/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

class DataRepository @Inject constructor(
    private val apiRajaOngkir: ApiRajaOngkir
) {

    suspend fun getCities(): CityResponse {
        //insert your API Key
        return apiRajaOngkir.getCities(API_KEY)
    }

    suspend fun getCost(
        origin: String,
        destination: String,
        weight: Int,
        courier: String
    ): CostResponse {
        //insert your API Key
        return apiRajaOngkir.getCost(API_KEY, origin, destination, weight, courier)
    }
}