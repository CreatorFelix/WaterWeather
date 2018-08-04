package com.creator.waterweather.view.activity

import android.os.Bundle
import com.creator.waterweather.R
import com.creator.waterweather.contract.WelcomeContract
import com.creator.waterweather.view.BaseActivity
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : BaseActivity(), WelcomeContract.View {

    override lateinit var presenter: WelcomeContract.Presenter

    override var activity: BaseActivity? = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.start()
    }

    override fun showWelcomePages() {
        setContentView(R.layout.activity_welcome)
        btnSkip.setOnClickListener {
            presenter.saveLaunchState()
        }
    }

    override fun navigateToHomePage(){}
}