package com.stimednp.mvvmrajaongkir.data.repositories

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

    suspend fun getCities(apiKey: String): CityResponse {
        return apiRajaOngkir.getCities(apiKey)
    }

    suspend fun getCost(
        apiKey: String,
        origin: String,
        destination: String,
        weight: Int,
        courier: String
    ): CostResponse {
        return apiRajaOngkir.getCost(apiKey, origin, destination, weight, courier)
    }
}