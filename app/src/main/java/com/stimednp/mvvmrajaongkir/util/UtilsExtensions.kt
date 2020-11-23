package com.stimednp.mvvmrajaongkir.util

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import java.text.NumberFormat

/**
 * Created by rivaldy on Nov/16/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */


fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Context.myToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun <T> Context.openActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
    val intent = Intent(this, it)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
}

fun Any.toRupiah(): String {
    val localId = localID()
    val formatter = NumberFormat.getCurrencyInstance(localId)
    return formatter.format(this)
}