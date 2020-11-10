package com.dimonkiv.idictionary.utills

import android.content.Context
import com.dimonkiv.idictionary.data.db.DictionaryDatabase
import com.dimonkiv.idictionary.data.source.implementation.CardLocalDataSource
import com.dimonkiv.idictionary.data.source.implementation.WordsLocalDataSource

object Injection {

    fun provideWordsDataSource(context: Context): WordsLocalDataSource {
        val database = DictionaryDatabase.getInstance(context)
        return WordsLocalDataSource.getInstance(AppExecutors(), database.wordDao)
    }

    fun provideCardDataSource(context: Context): CardLocalDataSource {
        val database = DictionaryDatabase.getInstance(context)
        return CardLocalDataSource.getInstance(AppExecutors(), database.cardDao)
    }
}