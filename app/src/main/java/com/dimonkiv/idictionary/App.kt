package com.dimonkiv.idictionary

import android.app.Application
import com.dimonkiv.idictionary.data.models.Card
import com.dimonkiv.idictionary.data.preference.MainPrefManager

class App : Application() {
    private lateinit var mainPrefManager: MainPrefManager

    override fun onCreate() {
        super.onCreate()
        mainPrefManager = MainPrefManager.getInstance(this)

        if (mainPrefManager.isFirstTimeLaunch()) {
            mainPrefManager.setFirstTimeLaunch(false)
        }
    }
}