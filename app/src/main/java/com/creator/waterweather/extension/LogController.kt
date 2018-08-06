package com.creator.waterweather.extension

import android.util.Log
import com.creator.waterweather.BuildConfig

const val LOG_TAG = "water_weather"

fun verbose(msg: String) = Log.v(LOG_TAG, msg)

fun debug(msg: String) {
    if (BuildConfig.DEBUG) Log.d(LOG_TAG, msg)
}

fun info(msg: String) = Log.i(LOG_TAG, msg)

fun warning(msg: String) = Log.w(LOG_TAG, msg)

fun errorLog(msg: String) = Log.e(LOG_TAG, msg)