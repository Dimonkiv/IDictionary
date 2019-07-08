package com.dimonkiv.idictionary.data.datasources

import com.dimonkiv.idictionary.utills.FirebaseTableNames
import com.dimonkiv.idictionary.data.models.Card
import com.google.firebase.database.DatabaseReference

class CardDataSource(private val databaseReference: DatabaseReference) {

    fun insert(card: Card) {
        val key = databaseReference.child(FirebaseTableNames.CARDS).push().key

        key?.let {
            card.id = it
            databaseReference.child(FirebaseTableNames.CARDS).child(it).setValue(card)
        }
    }
}