package com.baleno.android.startup

import android.app.Application
import com.baleno.android.startup.manager.StartupManager
import com.baleno.android.startup.tasks.Task1
import com.baleno.android.startup.tasks.Task2
import com.baleno.android.startup.tasks.Task3
import com.baleno.android.startup.tasks.Task4
import com.baleno.android.startup.tasks.Task5
import com.baleno.android.startup.util.LogUtil

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        StartupManager.Builder()
            .addStartup(Task1())
            .addStartup(Task2())
            .addStartup(Task3())
            .addStartup(Task4())
            .addStartup(Task5())
            .build(this)
            .start()

        LogUtil.log("onCreate End.")
    }
}