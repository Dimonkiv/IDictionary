package com.dimonkiv.idictionary.data.datasources.implementation

import com.dimonkiv.idictionary.data.datasources.IWordDataSource
import com.dimonkiv.idictionary.data.models.Word
import com.dimonkiv.idictionary.utills.FirebaseTableNames
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class WordDataSource(private val reference: DatabaseReference) : IWordDataSource {

    override fun insert(word: Word) {
        val key = reference.child(FirebaseTableNames.WORDS).push().key

        key?.let {
            word.id = it
            reference.child(FirebaseTableNames.WORDS).child(it).setValue(word)
        }
    }

    override fun getAll(onResult: (List<Word>) -> Unit) {
        reference.child(FirebaseTableNames.WORDS)
                .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(snapshot: DataSnapshot) {
                        snapshot.run {
                            val words = children.mapNotNull { it.getValue(Word::class.java) }
                            onResult(words)
                        }
                    }

                })
    }

    override fun getById(id: String, onResult: (Word) -> Unit) {
        reference
                .child(FirebaseTableNames.WORDS)
                .child(id)
                .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(snapshot: DataSnapshot) {
                        val word = snapshot.getValue(Word::class.java)
                        word?.run { onResult(word) }
                    }

                })
    }
}