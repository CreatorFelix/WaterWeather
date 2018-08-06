package com.creator.waterweather.extension

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager

fun Activity.applyImmersiveStatusBar() {
    if (window != null) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        window.decorView.systemUiVisibility =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                else View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = Color.TRANSPARENT
}