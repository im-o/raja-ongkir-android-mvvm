package com.stimednp.mvvmrajaongkir.usecase

import com.stimednp.mvvmrajaongkir.model.CityResponse
import com.stimednp.mvvmrajaongkir.model.CostResponse
import com.stimednp.mvvmrajaongkir.model.ProvinceResponse
import com.stimednp.mvvmrajaongkir.model.ResultData
import com.stimednp.mvvmrajaongkir.repositories.DataRepository
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

    suspend fun getProvinces(apiKey: String): ResultData<ProvinceResponse> {
        val provinceResponse = dataRepository.getProvinces(apiKey)
        return try {
            if (provinceResponse.rajaOngkir?.status?.code == STATUS_OK) {
                ResultData.Success(provinceResponse)
            } else {
                ResultData.Failed(provinceResponse.rajaOngkir?.status?.description)
            }
        } catch (e: Exception) {
            ResultData.Exception(e.message)
        }
    }

    suspend fun getCities(apiKey: String): ResultData<CityResponse> {
        return try {
            val cityResponse = dataRepository.getCities(apiKey)
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
        apiKey: String,
        origin: String,
        destination: String,
        weight: Int,
        courier: String
    ): ResultData<CostResponse> {
        return try {
            val costResponse = dataRepository.getCost(apiKey, origin, destination, weight, courier)
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