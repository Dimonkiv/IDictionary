package com.dimonkiv.idictionary

import android.app.Application
import com.dimonkiv.idictionary.data.FirebaseManager
import com.dimonkiv.idictionary.data.models.Card
import com.dimonkiv.idictionary.data.preference.MainPrefManager

class App : Application() {
    private lateinit var mainPrefManager: MainPrefManager

    override fun onCreate() {
        super.onCreate()
        mainPrefManager = MainPrefManager.getInstance(this)

        if (mainPrefManager.isFirstTimeLaunch()) {
            initDatabase()
            mainPrefManager.setFirstTimeLaunch(false)
        }
    }

    private fun initDatabase() {
        val cardDataSource = FirebaseManager.getInstance().getCardDataSource()

        var card = Card("", "General")
        cardDataSource.insert(card)

        card = Card("", "Family")
        cardDataSource.insert(card)

        card = Card("", "Business")
        cardDataSource.insert(card)

        card = Card("", "Weather")
        cardDataSource.insert(card)
    }
}