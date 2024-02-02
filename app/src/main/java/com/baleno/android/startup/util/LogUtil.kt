package com.baleno.android.startup.util

import android.util.Log

object LogUtil {
    fun log(tag: String, message: String) {
        Log.d(tag, message)
    }

    fun log(message: String) {
        log("TAG", message)
    }
}