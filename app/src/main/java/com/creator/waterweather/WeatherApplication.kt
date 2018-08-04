package com.creator.waterweather

import android.app.Application
import com.orhanobut.hawk.Hawk
import com.squareup.leakcanary.LeakCanary

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initLeakDetection()
        initHawk()
    }

    private fun initLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this)
        }
    }

    private fun initHawk() = Hawk.init(this).build()
}