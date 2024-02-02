package com.baleno.android.startup.model

import com.baleno.android.startup.Startup

data class StartupSortStore(
    val result: MutableList<Startup<*>>, // 排序后的结果
    val startupMap: Map<String, Startup<*>>, // 任务原图
    val startupChildrenMap: Map<String, MutableList<String>> //当前任务依赖表
)