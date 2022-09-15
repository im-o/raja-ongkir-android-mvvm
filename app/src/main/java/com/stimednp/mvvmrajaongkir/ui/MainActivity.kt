package com.stimednp.mvvmrajaongkir.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.stimednp.mvvmrajaongkir.R
import com.stimednp.mvvmrajaongkir.data.model.CostPostageFee
import com.stimednp.mvvmrajaongkir.data.model.ResultData
import com.stimednp.mvvmrajaongkir.data.model.city.CityResult
import com.stimednp.mvvmrajaongkir.data.model.cost.CostRajaOngkir
import com.stimednp.mvvmrajaongkir.databinding.ActivityMainBinding
import com.stimednp.mvvmrajaongkir.util.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var listCity: List<CityResult?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadCities()
    }

    private fun initClick() {
        binding.checkPostageMB.setOnClickListener { checkedPostageFee() }
    }

    private fun loadCities() {
        val listCity = mainViewModel.getCities()
        listCity.observe(this) {
            when (it) {
                is ResultData.Success -> {
                    binding.loadingPB.gone()
                    initSpinner(it.data?.rajaOngkir?.results ?: return@observe)
                }
                is ResultData.Loading -> binding.loadingPB.visible()
                is ResultData.Failed -> showErrorMessage(it.message.toString())
                is ResultData.Exception -> showErrorMessage(it.message.toString())
            }
        }
    }

    private fun initSpinner(cityResults: List<CityResult?>) {
        listCity = cityResults
        val cities = mutableListOf<String>()
        val couriers = resources.getStringArray(R.array.list_courier)
        for (i in cityResults.indices) cities.add(cityResults[i]?.cityName ?: "")
        val cityAdapter = ArrayAdapter(this, R.layout.item_spinner_dropdown, cities)
        val courierAdapter = ArrayAdapter(this, R.layout.item_spinner_dropdown, couriers)

        binding.destinationAutoCompleteTV.setAdapter(cityAdapter)
        binding.originAutoCompleteTV.setAdapter(cityAdapter)
        binding.courierAutoCompleteTV.setAdapter(courierAdapter)
        initClick()
    }

    private fun checkedForm(selectedCity: String): CityResult? {
        var city: CityResult? = null
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
            val courier = strCourier.lowercase(localID())
            val weight = strWeight.toInt()
            val idOriginCity = originCity?.cityId ?: ""
            val idDestinationCity = destinationCity?.cityId ?: ""

            checkedCost(idOriginCity, idDestinationCity, weight, courier)
        } else {
            myToast(getString(R.string.form_empty))
        }
    }

    private fun checkedCost(origin: String, destination: String, weight: Int, courier: String) {
        val costData = mainViewModel.getCost(origin, destination, weight, courier)
        costData.observe(this) {
            when (it) {
                is ResultData.Success -> {
                    binding.loadingPB.gone()
                    setupAdapter(it.data?.rajaOngkir)
                }
                is ResultData.Loading -> binding.loadingPB.visible()
                is ResultData.Failed -> showErrorMessage(it.message.toString())
                is ResultData.Exception -> showErrorMessage(it.message.toString())
            }
        }
    }

    private fun setupAdapter(rajaOngkir: CostRajaOngkir?) {
        val listPostageFee = arrayListOf<CostPostageFee>()
        for (i in rajaOngkir?.results?.indices ?: return) {
            val costs = rajaOngkir.results[i].costs
            for (j in costs?.indices ?: return) {
                val cost = rajaOngkir.results[i].costs?.get(j)?.cost
                for (k in cost?.indices ?: return) {
                    val code = rajaOngkir.results[i].code
                    val name = rajaOngkir.results[i].name
                    val service = costs[j].service
                    val description = costs[j].description
                    val value = cost[k].value
                    val etd = cost[k].etd
                    listPostageFee.add(CostPostageFee(code, name, service, description, etd, value))
                }
            }
        }

        openActivity(PostageFeeActivity::class.java) {
            putParcelableArrayList(PostageFeeActivity.DATA_POSTAGE_FEE, listPostageFee)
            putParcelable(PostageFeeActivity.DATA_ORIGIN, rajaOngkir.originDetails)
            putParcelable(PostageFeeActivity.DATA_DESTINATION, rajaOngkir.destinationDetails)
            putString(PostageFeeActivity.DATA_COURIER_NAME, rajaOngkir.query?.courier)
        }
    }

    private fun showErrorMessage(message: String) {
        binding.loadingPB.gone()
        myToast(getString(R.string.message_error, message))
    }
}