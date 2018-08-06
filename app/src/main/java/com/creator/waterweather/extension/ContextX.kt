package com.creator.waterweather.extension

import android.app.Service
import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast


fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Context.isNetworkConnected(): Boolean {
    val cm = getSystemService(Service.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm.activeNetworkInfo.isConnected
}

val Context.httpCacheDir: String
    get() = "${externalCacheDir.absolutePath}/httpCache"