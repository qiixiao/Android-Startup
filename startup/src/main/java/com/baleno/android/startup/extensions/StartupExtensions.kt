package com.baleno.android.startup.extensions

import com.baleno.android.startup.Startup

private const val DEFAULT_KEY = "com.baleno.android_startup.defaultKey"

internal fun Class<out Startup<*>>.getUniqueKey(): String {
    return "$DEFAULT_KEY:$name"
}

internal fun String.getUniqueKey(): String = "$DEFAULT_KEY:$this"