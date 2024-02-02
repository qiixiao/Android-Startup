package com.baleno.android.startup.sort

import com.baleno.android.startup.Startup
import com.baleno.android.startup.model.StartupSortStore
import com.baleno.android.startup.exception.StartupException
import com.baleno.android.startup.extensions.getUniqueKey
import java.util.ArrayDeque

object TopologySort {
    fun sort(startupList: List<Startup<*>>): StartupSortStore {
        // 任务表 - 用于保存原图
        val startupMap = hashMapOf<String, Startup<*>>()
        // 0度表
        val zeroDeque = ArrayDeque<String>()
        // 任务依赖表
        val startupChildrenMap = hashMapOf<String, MutableList<String>>()
        // 入度表
        val inDegreeMap = hashMapOf<String, Int>()

        startupList.forEach {
            val uniqueKey = it::class.java.getUniqueKey()
            if (startupMap.containsKey(uniqueKey)) {
                throw StartupException("$it multiple add.")
            }
            startupMap[uniqueKey] = it

            // 计算每个任务的入度数（依赖任务数）
            val dependenciesCount = it.getDependenciesCount()
            inDegreeMap[uniqueKey] = dependenciesCount

            // 记录入度数（依赖的任务数）为0的任务
            if (dependenciesCount == 0) {
                zeroDeque.offer(uniqueKey)
            } else {
                // 遍历本任务的依赖任务，生成任务依赖表
                it.dependenciesByName()?.forEach { parent ->
                    val parentUniqueKey = parent.getUniqueKey()
                    if (startupChildrenMap[parentUniqueKey] == null) {
                        startupChildrenMap[parentUniqueKey] = arrayListOf()
                    }
                    // 保存父任务的依赖关系
                    startupChildrenMap[parentUniqueKey]?.add(uniqueKey)
                }
            }
        }

        // 删除图中入度为0的节点，并更新全图，最后完成排序
        val result = mutableListOf<Startup<*>>()
        while (!zeroDeque.isEmpty()) {
            zeroDeque.poll()?.let { uniqueKey ->
                startupMap[uniqueKey]?.let {
                    result.add(it)
                }

                // 删除此入度为0的任务
                startupChildrenMap[uniqueKey]?.forEach { child ->
                    inDegreeMap[child] = inDegreeMap[child]?.minus(1) ?: 0
                    if (inDegreeMap[child] == 0) {
                        zeroDeque.offer(child)
                    }
                }
            }
        }

        return StartupSortStore(result, startupMap, startupChildrenMap)
    }
}