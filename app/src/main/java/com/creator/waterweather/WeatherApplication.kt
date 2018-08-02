package com.creator.waterweather

import android.app.Application
import com.squareup.leakcanary.LeakCanary

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initLeakDetection()
    }

    private fun initLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this)
        }
    }
}