package com.stimednp.mvvmrajaongkir.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by rivaldy on Nov/16/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

@Parcelize
data class CostPostageFee(
    val code: String? = null,
    val name: String? = null,
    val service: String? = null,
    val description: String? = null,
    val etd: String? = null,
    val value: Int? = null
) : Parcelable