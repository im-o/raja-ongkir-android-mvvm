package com.stimednp.mvvmrajaongkir.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.stimednp.mvvmrajaongkir.APIKey.Companion.API_KEY
import com.stimednp.mvvmrajaongkir.R
import com.stimednp.mvvmrajaongkir.databinding.ActivityMainBinding
import com.stimednp.mvvmrajaongkir.model.CityResponse
import com.stimednp.mvvmrajaongkir.model.ResultData
import com.stimednp.mvvmrajaongkir.util.gone
import com.stimednp.mvvmrajaongkir.util.loge
import com.stimednp.mvvmrajaongkir.util.myToast
import com.stimednp.mvvmrajaongkir.util.visible
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var listCity: List<CityResponse.RajaOngkir.Result?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        initClick()
    }

    private fun initClick() {
        binding.checkPostageMB.setOnClickListener { checkedPostageFee() }
    }

    private fun initSpinner(cityResults: List<CityResponse.RajaOngkir.Result?>) {
        listCity = cityResults
        val cities = mutableListOf<String>()
        val couriers = resources.getStringArray(R.array.list_courier)
        for (i in cityResults.indices) cities.add(cityResults[i]?.cityName ?: "")
        val cityAdapter = ArrayAdapter(this, R.layout.item_spinner_dropdown, cities)
        val courierAdapter = ArrayAdapter(this, R.layout.item_spinner_dropdown, couriers)

        binding.destinationAutoCompleteTV.setAdapter(cityAdapter)
        binding.originAutoCompleteTV.setAdapter(cityAdapter)
        binding.courierAutoCompleteTV.setAdapter(courierAdapter)
    }

    private fun initViewModel() {
        val listCity = mainViewModel.getCities(API_KEY)
        listCity.observe(this, {
            when (it) {
                is ResultData.Loading -> {
                    binding.loadingPB.visible()
                }
                is ResultData.Success -> {
                    binding.loadingPB.gone()
                    initSpinner(it.data?.rajaOngkir?.results ?: return@observe)
                }
                else -> {
                    binding.loadingPB.gone()
                    loge("SOMETHING ERROR : $it")
                }
            }
        })
    }

    private fun checkedForm(selectedCity: String): CityResponse.RajaOngkir.Result? {
        var city: CityResponse.RajaOngkir.Result? = null
        val dataCity = listCity.filter {
            it?.cityName?.contains(selectedCity) ?: false
        }

        for (i in dataCity.indices) {
            val matchedCity = dataCity[i]?.cityName ?: ""
            if (selectedCity == matchedCity) city = dataCity[i]
        }
        return city
    }

    private fun checkedPostageFee() {
        val strOriginCity = binding.originAutoCompleteTV.text.toString()
        val strDestinationCity = binding.destinationAutoCompleteTV.text.toString()
        val strCourier = binding.courierAutoCompleteTV.text.toString()
        val strWeight = binding.weightTIET.text.toString()

        if (strDestinationCity.isNotEmpty() && strOriginCity.isNotEmpty() && strCourier.isNotEmpty() && strWeight.isNotEmpty()) {
            val originCity = checkedForm(strOriginCity)
            val destinationCity = checkedForm(strDestinationCity)

            val courier = strCourier.toLowerCase(Locale.US)
            val weight = strWeight.toInt()
            val idOriginCity = originCity?.cityId ?: ""
            val idDestinationCity = destinationCity?.cityId ?: ""

            checkedCost(idOriginCity, idDestinationCity, weight, courier)
        } else {
            myToast(getString(R.string.form_empty))
        }
    }

    private fun checkedCost(origin: String, destination: String, weight: Int, courier: String) {
        val costData = mainViewModel.getCost(API_KEY, origin, destination, weight, courier)
        costData.observe(this, {
            when (it) {
                is ResultData.Loading -> {
                    binding.loadingPB.visible()
                }
                is ResultData.Success -> {
                    binding.loadingPB.gone()
                    loge("COST : ${it.data?.rajaOngkir?.results?.size}")
                }
                else -> {
                    binding.loadingPB.gone()
                    loge("SOMETHING ERROR : $it")
                }
            }
        })
    }
}