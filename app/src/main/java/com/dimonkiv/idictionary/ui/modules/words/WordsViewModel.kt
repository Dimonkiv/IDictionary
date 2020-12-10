package com.dimonkiv.idictionary.ui.modules.words

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dimonkiv.idictionary.data.models.Card
import com.dimonkiv.idictionary.data.models.Word
import com.dimonkiv.idictionary.data.source.WordDataSource

class WordsViewModel(
    private val wordsDataSource: WordDataSource
) : ViewModel() {

    lateinit var card: Card
    private lateinit var _words: MutableLiveData<List<Word>>

    fun getWords(): MutableLiveData<List<Word>> {
        if (!::_words.isInitialized) {
            _words = MutableLiveData()
            loadData()
        }

        return _words
    }

    private fun loadData() {
        wordsDataSource.getWordsByCardId(card.id, object : WordDataSource.LoadWordsDataSource {
            override fun onLoadWords(words: List<Word>) {
                _words.value = words
            }

            override fun onDataNotAvailable() {

            }

        })
    }
}