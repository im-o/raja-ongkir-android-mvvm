package com.stimednp.mvvmrajaongkir.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.stimednp.mvvmrajaongkir.databinding.ActivityPostageFeeBinding
import com.stimednp.mvvmrajaongkir.model.CostPostageFee
import com.stimednp.mvvmrajaongkir.model.CostResponse

class PostageFeeActivity : AppCompatActivity() {
    companion object {
        const val DATA_POSTAGE_FEE = "DATA_POSTAGE_FEE"
        const val DATA_ORIGIN = "DATA_ORIGIN"
        const val DATA_DESTINATION = "DATA_DESTINATION"
    }

    private val postageFeeAdapter: PostageFeeAdapter by lazy { PostageFeeAdapter() }
    private lateinit var binding: ActivityPostageFeeBinding
    private var costPostageFee: ArrayList<CostPostageFee>? = null
    private var originCity: CostResponse.RajaOngkir.OriginDetails? = null
    private var destinationCity: CostResponse.RajaOngkir.DestinationDetails? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostageFeeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        costPostageFee = intent.getParcelableArrayListExtra(DATA_POSTAGE_FEE)
        originCity = intent.getParcelableExtra(DATA_ORIGIN)
        destinationCity = intent.getParcelableExtra(DATA_DESTINATION)
        initView(originCity, destinationCity)
        setupAdapter(costPostageFee ?: return)
    }

    private fun initView(originCity: CostResponse.RajaOngkir.OriginDetails?, destinationCity: CostResponse.RajaOngkir.DestinationDetails?) {
        val strTransportationLine = "${originCity?.cityName} - ${destinationCity?.cityName}"
        binding.transportationLineTV.text = strTransportationLine
    }

    private fun setupAdapter(listPostageFee: ArrayList<CostPostageFee>) {
        postageFeeAdapter.submitList(listPostageFee)
        postageFeeAdapter.notifyDataSetChanged()
        binding.costListRV.apply {
            layoutManager = LinearLayoutManager(this@PostageFeeActivity)
            setHasFixedSize(true)
            adapter = postageFeeAdapter
        }
    }
}