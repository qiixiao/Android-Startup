package com.baleno.android.startup.tasks

import android.content.Context
import android.os.Looper
import android.os.SystemClock
import com.baleno.android.startup.AndroidStartup
import com.baleno.android.startup.util.LogUtil

class Task1 : AndroidStartup<String>() {
    override fun create(context: Context): String? {
        val threadName = if (Looper.myLooper() == Looper.getMainLooper()) "主线程" else "子线程"
        LogUtil.log("$threadName - Task1 开始")
        SystemClock.sleep(1_000)
        LogUtil.log("$threadName - Task1 完成")
        return "Task1返回数据"
    }
}