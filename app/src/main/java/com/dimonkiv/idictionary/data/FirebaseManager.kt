package com.dimonkiv.idictionary.data

import com.dimonkiv.idictionary.data.datasources.CardDataSource
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FirebaseManager {

    companion object {
        private var instance: FirebaseManager? = null

        private lateinit var databaseReference: DatabaseReference
        private lateinit var cardDataSource: CardDataSource

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
        }

    }

    fun getCardDataSource(): CardDataSource {
        return cardDataSource
    }
}