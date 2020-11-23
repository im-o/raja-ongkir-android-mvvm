package com.stimednp.mvvmrajaongkir.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.stimednp.mvvmrajaongkir.R
import com.stimednp.mvvmrajaongkir.databinding.ActivityPostageFeeBinding
import com.stimednp.mvvmrajaongkir.model.CostPostageFee
import com.stimednp.mvvmrajaongkir.model.CostResponse
import com.stimednp.mvvmrajaongkir.util.gone
import com.stimednp.mvvmrajaongkir.util.visible
import java.util.*

class PostageFeeActivity : AppCompatActivity() {
    companion object {
        const val DATA_POSTAGE_FEE = "DATA_POSTAGE_FEE"
        const val DATA_ORIGIN = "DATA_ORIGIN"
        const val DATA_DESTINATION = "DATA_DESTINATION"
        const val DATA_COURIER_NAME = "DATA_COURIER_NAME"
    }

    private val postageFeeAdapter: PostageFeeAdapter by lazy { PostageFeeAdapter() }
    private lateinit var binding: ActivityPostageFeeBinding
    private var costPostageFee: ArrayList<CostPostageFee>? = null
    private var originCity: CostResponse.RajaOngkir.OriginDetails? = null
    private var destinationCity: CostResponse.RajaOngkir.DestinationDetails? = null
    private var courierName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostageFeeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        costPostageFee = intent.getParcelableArrayListExtra(DATA_POSTAGE_FEE)
        originCity = intent.getParcelableExtra(DATA_ORIGIN)
        destinationCity = intent.getParcelableExtra(DATA_DESTINATION)
        courierName = intent.getStringExtra(DATA_COURIER_NAME)

        initView(originCity, destinationCity, courierName)
        setupAdapter(costPostageFee ?: return)
    }

    private fun initView(originCity: CostResponse.RajaOngkir.OriginDetails?, destinationCity: CostResponse.RajaOngkir.DestinationDetails?, courierName: String?) {
        val strTransportationLine = "${originCity?.cityName} - ${destinationCity?.cityName}"
        binding.transportationLineTV.text = strTransportationLine
        binding.noDataTV.text = getString(R.string.courier_not_available, courierName?.toUpperCase(Locale.US))
    }

    private fun setupAdapter(listPostageFee: ArrayList<CostPostageFee>) {
        if (listPostageFee.size > 0) binding.noDataTV.gone() else binding.noDataTV.visible()
        postageFeeAdapter.submitList(listPostageFee)
        binding.costListRV.apply {
            layoutManager = LinearLayoutManager(this@PostageFeeActivity)
            setHasFixedSize(true)
            adapter = postageFeeAdapter
        }
    }
}