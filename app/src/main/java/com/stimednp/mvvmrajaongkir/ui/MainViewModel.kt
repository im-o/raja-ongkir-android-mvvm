package com.stimednp.mvvmrajaongkir.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.stimednp.mvvmrajaongkir.data.model.ResultData
import com.stimednp.mvvmrajaongkir.data.model.city.CityResponse
import com.stimednp.mvvmrajaongkir.data.model.cost.CostResponse
import com.stimednp.mvvmrajaongkir.data.usecase.DataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by rivaldy on Nov/15/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: DataUseCase
) : ViewModel() {

    fun getCities(): LiveData<ResultData<CityResponse>> {
        return liveData {
            emit(ResultData.Loading())
            emit(useCase.getCities())
        }
    }

    fun getCost(
        origin: String,
        destination: String,
        weight: Int,
        courier: String
    ): LiveData<ResultData<CostResponse>> {
        return liveData {
            emit(ResultData.Loading())
            emit(useCase.getCost(origin, destination, weight, courier))
        }
    }
}