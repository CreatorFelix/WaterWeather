package com.creator.waterweather.base

import com.creator.waterweather.view.BaseActivity

interface BaseView<T> {

    var presenter: T

    var activity: BaseActivity?
}