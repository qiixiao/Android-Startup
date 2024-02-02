package com.baleno.android.startup.manager

import com.baleno.android.startup.Startup
import com.baleno.android.startup.model.ResultModel
import java.util.concurrent.ConcurrentHashMap

class StartupCacheManager {

    /**
     * Save initialized components result.
     */
    private val mInitializedComponents = ConcurrentHashMap<Class<out Startup<*>>, ResultModel<*>>()

    companion object {
        @JvmStatic
        val instance by lazy { StartupCacheManager() }
    }

    /**
     * save result of initialized component.
     */
    internal fun saveInitializedComponent(zClass: Class<out Startup<*>>, result: ResultModel<*>) {
        mInitializedComponents[zClass] = result
    }

    /**
     * check initialized.
     */
    fun hadInitialized(zClass: Class<out Startup<*>>): Boolean = mInitializedComponents.containsKey(zClass)

    @Suppress("UNCHECKED_CAST")
    fun <T> obtainInitializedResult(zClass: Class<out Startup<*>>): T? = mInitializedComponents[zClass]?.result as? T?

    fun remove(zClass: Class<out Startup<*>>) {
        mInitializedComponents.remove(zClass)
    }

    fun clear() {
        mInitializedComponents.clear()
    }
}