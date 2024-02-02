package com.baleno.android.startup.manager

import android.content.Context
import android.os.Looper
import com.baleno.android.startup.AndroidStartup
import com.baleno.android.startup.model.ResultModel
import com.baleno.android.startup.model.StartupSortStore
import com.baleno.android.startup.sort.TopologySort

class StartupManager private constructor(
    private val context: Context,
    private val startupList: List<AndroidStartup<*>>,
) {
    private lateinit var startupStore: StartupSortStore

    fun start(): StartupManager {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw RuntimeException("请在主线程调用")
        }
        startupStore = TopologySort.sort(startupList)
        startupStore.result.forEach { startup ->
            val result = startup.create(context)

            // To save result of initialized component.
            StartupCacheManager.instance.saveInitializedComponent(startup::class.java, ResultModel(result))
        }
        return this
    }

    class Builder {
        private var mStartupList = mutableListOf<AndroidStartup<*>>()

        fun addStartup(startup: AndroidStartup<*>) = apply {
            mStartupList.add(startup)
        }

        fun addAllStartup(list: List<AndroidStartup<*>>) = apply {
            list.forEach {
                addStartup(it)
            }
        }

        fun build(context: Context): StartupManager {
            return StartupManager(
                context,
                mStartupList
            )
        }
    }
}