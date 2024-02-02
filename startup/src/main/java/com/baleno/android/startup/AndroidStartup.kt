package com.baleno.android.startup

abstract class AndroidStartup<T> : Startup<T> {
    override fun dependenciesByName(): List<String>? = null

    override fun getDependenciesCount(): Int {
        if (dependenciesByName().isNullOrEmpty()) return dependenciesByName()?.size ?: 0
        return dependenciesByName()?.size ?: 0
    }
}