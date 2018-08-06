package com.creator.waterweather.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.annotation.NonNull

fun Context.navigateTo(@NonNull target: Class<*>, finish: Boolean = false) {
    val intent = Intent(this, target)
    if (this !is Activity) intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
    if (finish && this is Activity) finish()
}