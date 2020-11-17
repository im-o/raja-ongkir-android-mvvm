package com.stimednp.mvvmrajaongkir.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.stimednp.mvvmrajaongkir.databinding.ItemCostBinding
import com.stimednp.mvvmrajaongkir.model.CostPostageFee
import com.stimednp.mvvmrajaongkir.model.DiffUtilCost

/**
 * Created by rivaldy on Nov/16/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

class PostageFeeAdapter : ListAdapter<CostPostageFee, PostageFeeAdapter.CostViewHolder>(DiffUtilCost()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CostViewHolder {
        val view = ItemCostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CostViewHolder(view)
    }

    override fun onBindViewHolder(holder: CostViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindItem(item)
    }

    class CostViewHolder(private val binding: ItemCostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: CostPostageFee) {
            binding.apply {
                codeTV.text = item.code
                serviceTV.text = item.service
                serviceDescriptionTV.text = item.description
                costValueTV.text = item.value.toString()
                estimationTV.text = item.etd
            }
        }
    }
}