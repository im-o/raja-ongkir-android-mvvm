package com.stimednp.mvvmrajaongkir.data.usecase

import com.stimednp.mvvmrajaongkir.data.model.ResultData
import com.stimednp.mvvmrajaongkir.data.model.city.CityResponse
import com.stimednp.mvvmrajaongkir.data.model.cost.CostResponse
import com.stimednp.mvvmrajaongkir.data.repositories.DataRepository
import javax.inject.Inject

/**
 * Created by rivaldy on Nov/15/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

class DataUseCase @Inject constructor(
    private val dataRepository: DataRepository
) {

    companion object {
        const val STATUS_OK = 200
    }

    suspend fun getCities(): ResultData<CityResponse> {
        return try {
            val cityResponse = dataRepository.getCities()
            if (cityResponse.rajaOngkir?.status?.code == STATUS_OK) {
                ResultData.Success(cityResponse)
            } else {
                ResultData.Failed(cityResponse.rajaOngkir?.status?.description)
            }
        } catch (e: Exception) {
            ResultData.Exception(e.message)
        }
    }

    suspend fun getCost(
        origin: String,
        destination: String,
        weight: Int,
        courier: String
    ): ResultData<CostResponse> {
        return try {
            val costResponse = dataRepository.getCost(origin, destination, weight, courier)
            if (costResponse.rajaOngkir?.status?.code == STATUS_OK) {
                ResultData.Success(costResponse)
            } else {
                ResultData.Failed(costResponse.rajaOngkir?.status?.description)
            }
        } catch (e: Exception) {
            ResultData.Exception(e.message)
        }
    }
}