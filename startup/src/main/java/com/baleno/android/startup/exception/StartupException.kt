package com.baleno.android.startup.exception

internal class StartupException : RuntimeException {

    constructor(message: String?) : super(message)

    constructor(t: Throwable) : super(t)
}