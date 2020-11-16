package com.stimednp.mvvmrajaongkir.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.stimednp.mvvmrajaongkir.model.CityResponse
import com.stimednp.mvvmrajaongkir.model.CostResponse
import com.stimednp.mvvmrajaongkir.model.ProvinceResponse
import com.stimednp.mvvmrajaongkir.model.ResultData
import com.stimednp.mvvmrajaongkir.usecase.DataUseCase

/**
 * Created by rivaldy on Nov/15/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

class MainViewModel @ViewModelInject constructor(
    private val useCase: DataUseCase
) : ViewModel() {

    fun getProvinces(apiKey: String): LiveData<ResultData<ProvinceResponse>> {
        return liveData {
            emit(ResultData.Loading())
            emit(useCase.getProvinces(apiKey))
        }
    }

    fun getCities(apiKey: String): LiveData<ResultData<CityResponse>> {
        return liveData {
            emit(ResultData.Loading())
            emit(useCase.getCities(apiKey))
        }
    }

    fun getCost(
        apiKey: String,
        origin: String,
        destination: String,
        weight: Int,
        courier: String
    ): LiveData<ResultData<CostResponse>> {
        return liveData {
            emit(ResultData.Loading())
            emit(useCase.getCost(apiKey, origin, destination, weight, courier))
        }
    }
}