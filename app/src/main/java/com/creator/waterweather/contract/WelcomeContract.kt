package com.creator.waterweather.contract

import com.creator.waterweather.base.BasePresenter
import com.creator.waterweather.base.BaseView

interface WelcomeContract {

    interface View : BaseView<Presenter> {

        fun navigateToHomePage()

        fun showWelcomePages()
    }

    interface Presenter : BasePresenter {

        fun saveLaunchState()
    }
}