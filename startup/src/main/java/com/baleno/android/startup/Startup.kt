package com.baleno.android.startup

import android.content.Context
import java.util.concurrent.Executor

interface Startup<T> {
    fun create(context: Context): T?

    /**
     * 依赖任务的ClassName
     */
    fun dependenciesByName(): List<String>?

    /**
     * 入度数
     */
    fun getDependenciesCount(): Int

}