package com.creator.waterweather.data.source

import com.creator.waterweather.data.City
import io.reactivex.Flowable

interface CityDataSource {

    interface Local {

        fun getSelectedCity(): Flowable<List<City>>

        fun addUserCity(city: City)
    }

    interface Remote {

        fun getSuggestCities(): Flowable<List<City>>

        fun findCitiesByKeywords(keywords: String): Flowable<List<City>>
    }
}