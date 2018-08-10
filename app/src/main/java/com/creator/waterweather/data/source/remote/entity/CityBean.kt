package com.creator.waterweather.data.source.remote.entity

import com.creator.waterweather.data.City
import com.google.gson.annotations.SerializedName


data class CityBean(@SerializedName("HeWeather6")
                    val heWeather: List<HeWeatherItem>?)

data class HeWeatherItem(@SerializedName("basic")
                         val basic: List<BasicItem>?,
                         @SerializedName("status")
                         val status: String = "")

data class BasicItem(@SerializedName("admin_area")
                     val adminArea: String = "",
                     @SerializedName("tz")
                     val tz: String = "",
                     @SerializedName("location")
                     val location: String = "",
                     @SerializedName("lon")
                     val lon: String = "",
                     @SerializedName("parent_city")
                     val parentCity: String = "",
                     @SerializedName("type")
                     val type: String = "",
                     @SerializedName("cnty")
                     val cnty: String = "",
                     @SerializedName("lat")
                     val lat: String = "",
                     @SerializedName("cid")
                     val cid: String = "")

fun CityBean.toCityList(): List<City> {
    val cities = ArrayList<City>()
    if (heWeather != null && heWeather.isNotEmpty()) {
        heWeather[0].basic?.forEach {
            val city = City()
            city.name = it.location
            city.parentCity = it.parentCity
            city.adminArea = it.adminArea
            city.country = it.cnty
            cities.add(city)
        }
    }
    return cities
}




