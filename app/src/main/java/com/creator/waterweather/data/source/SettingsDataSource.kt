package com.creator.waterweather.data.source

import io.reactivex.Flowable

interface SettingsDataSource {

    interface Local {

        fun loadLaunchState(): Flowable<Boolean>

        fun saveLaunchState(): Flowable<Boolean>
    }
}