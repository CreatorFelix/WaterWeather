package com.creator.waterweather.widget

import android.graphics.PixelFormat
import android.graphics.drawable.Drawable

abstract class WeatherDrawable : Drawable() {

    abstract fun onPrepareNewFrame()

    override fun getOpacity(): Int = PixelFormat.TRANSPARENT
}