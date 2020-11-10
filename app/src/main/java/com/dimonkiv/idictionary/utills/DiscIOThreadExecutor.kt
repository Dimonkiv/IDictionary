package com.dimonkiv.idictionary.utills

import java.util.concurrent.Executor
import java.util.concurrent.Executors


/**Executor that run task on a new background thread*/
class DiscIOThreadExecutor : Executor {

    private val discIO = Executors.newSingleThreadExecutor()

    override fun execute(command: Runnable) {discIO.execute(command)}
}