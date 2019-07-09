package com.dimonkiv.idictionary.data.datasources.implementation

import com.dimonkiv.idictionary.data.datasources.ICardDataSource
import com.dimonkiv.idictionary.utills.FirebaseTableNames
import com.dimonkiv.idictionary.data.models.Card
import com.google.firebase.database.*

class CardDataSource(private val databaseReference: DatabaseReference): ICardDataSource {

    override fun insert(card: Card) {
        val key = databaseReference.child(FirebaseTableNames.CARDS).push().key

        key?.let {
            card.id = it
            databaseReference.child(FirebaseTableNames.CARDS).child(it).setValue(card)
        }
    }

    override fun getAll(onResult: (List<Card>) -> Unit) {
        databaseReference.child(FirebaseTableNames.CARDS)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {

                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    dataSnapshot.run {
                        val cards = children.mapNotNull {it.getValue(Card::class.java) }
                        onResult(cards)
                    }
                }

            })
    }
}