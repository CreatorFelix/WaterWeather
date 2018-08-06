package com.creator.waterweather.contract

import com.baidu.location.BDLocation
import com.creator.waterweather.base.BasePresenter
import com.creator.waterweather.base.BaseView
import com.creator.waterweather.data.City

interface HomeContract {

    interface Presenter : BasePresenter {

        fun loadSelectedCities()

        fun startLocation()

        fun permissionsReady(changed: Boolean = true)

        fun stopLocation()
    }

    interface View : BaseView<Presenter> {

        fun showPermissionRationale(permissions: Array<String>)

        fun notifyCityList(cities: List<City>)

        fun showLocation(location: BDLocation)
    }
}