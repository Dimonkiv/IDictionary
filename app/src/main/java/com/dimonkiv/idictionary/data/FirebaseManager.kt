package com.dimonkiv.idictionary.data

import com.dimonkiv.idictionary.data.datasources.implementation.CardDataSource
import com.dimonkiv.idictionary.data.datasources.implementation.WordDataSource
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FirebaseManager {

    companion object {
        private var instance: FirebaseManager? = null

        private lateinit var databaseReference: DatabaseReference
        private lateinit var cardDataSource: CardDataSource
        private lateinit var wordDataSource: WordDataSource

        fun getInstance(): FirebaseManager {
            if (instance == null) {
                instance = FirebaseManager()
                initFirebase()
                initDataSources()
            }

            return instance!!
        }

        private fun initFirebase() {
            databaseReference = FirebaseDatabase.getInstance().reference
        }

        private fun initDataSources() {
            cardDataSource  = CardDataSource(databaseReference)
            wordDataSource = WordDataSource(databaseReference)
        }

    }

    fun getCardDataSource(): CardDataSource {
        return cardDataSource
    }

    fun getWordDataSource(): WordDataSource {
        return wordDataSource
    }
}