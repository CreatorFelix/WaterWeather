package com.creator.waterweather.extension

import com.baidu.location.LocationClient
import com.baidu.location.LocationClientOption

fun LocationClient.applyDefaultOptions(): LocationClient {
    val option = LocationClientOption()
    option.locationMode = LocationClientOption.LocationMode.Battery_Saving
    option.scanSpan = 1000
    option.setIsNeedAddress(true)
    locOption = option
    return this
}