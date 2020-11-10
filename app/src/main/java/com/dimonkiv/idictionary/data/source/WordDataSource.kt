package com.dimonkiv.idictionary.data.source

import com.dimonkiv.idictionary.data.models.Word

interface WordDataSource{

    interface LoadWordsDataSource {
        fun onLoadWords(words: List<Word>)

        fun onDataNotAvailable()
    }

    interface LoadWordDataSource {
        fun onLoadWord(word: Word)

        fun onDataNotAvailable()
    }

    fun getWords(callback: LoadWordsDataSource)

    fun getWordsByCardId(cardId: String, callback: LoadWordsDataSource)

    fun getWord(id: String, callback: LoadWordDataSource)

    fun insertWord(word: Word)

    fun updateWord(word: Word)

    fun deleteWord(id: String)
}