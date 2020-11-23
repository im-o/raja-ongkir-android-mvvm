package com.stimednp.mvvmrajaongkir.data.model

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by rivaldy on Nov/16/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

class DiffUtilCost : DiffUtil.ItemCallback<CostPostageFee>() {
    override fun areItemsTheSame(oldItem: CostPostageFee, newItem: CostPostageFee): Boolean {
        return newItem.service == newItem.service
    }

    override fun areContentsTheSame(oldItem: CostPostageFee, newItem: CostPostageFee): Boolean {
        return newItem.service == newItem.service
    }
}