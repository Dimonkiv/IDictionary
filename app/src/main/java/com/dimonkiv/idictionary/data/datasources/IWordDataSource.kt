package com.dimonkiv.idictionary.data.datasources

import com.dimonkiv.idictionary.data.models.Word

interface IWordDataSource {


    fun insert(word: Word)

    fun getAll(onResult: (List<Word>) -> Unit)

    fun getAllByCardId(cardId: String, onResult: (List<Word>) -> Unit)

    fun getById(id: String, onResult: (Word) -> Unit)
}