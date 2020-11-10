package com.dimonkiv.idictionary.utills

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

/**Whole app executors*/
open class AppExecutors constructor(
        val discIO: Executor = DiscIOThreadExecutor(),
        val mainThread: Executor = MainThreadExecutor()
) {
    private class MainThreadExecutor : Executor {

        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }

    }
}