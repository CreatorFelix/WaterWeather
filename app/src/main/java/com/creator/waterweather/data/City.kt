package com.creator.waterweather.data

import com.creator.waterweather.data.source.local.greendao.entity.UserCity

class City {
    var name: String
    var parentCity: String
    var adminArea: String
    var country: String

    constructor(name: String = "",
                parentCity: String = "",
                adminArea: String = "",
                country: String = "") {
        this.name = name
        this.parentCity = parentCity
        this.adminArea = adminArea
        this.country = country
    }

    constructor(userCity: UserCity) {
        name = userCity.name
        parentCity = userCity.parentCity
        adminArea = userCity.adminArea
        country = userCity.country
    }

    fun toUserCity(): UserCity {
        val userCity = UserCity()
        userCity.name = name
        userCity.adminArea = adminArea
        userCity.parentCity = parentCity
        userCity.country = country
        return userCity
    }
}