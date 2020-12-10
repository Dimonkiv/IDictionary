package com.dimonkiv.idictionary.ui.modules.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.dimonkiv.idictionary.data.models.Word
import com.dimonkiv.idictionary.data.source.WordDataSource


class RepeatWordViewModel(private val wordDataSource: WordDataSource) : ViewModel() {

    private lateinit var words: MutableLiveData<List<Word>>
    private lateinit var isLoading: MutableLiveData<Boolean>


    /*--------------------------------------------------Get data------------------------------------------------------*/
    fun getWords(): MutableLiveData<List<Word>> {
        if (!::words.isInitialized) {
            words = MutableLiveData()
            loadWords()
        }

        return words
    }

    fun getIsLoading(): MutableLiveData<Boolean> {
        if(!::isLoading.isInitialized) {
            isLoading = MutableLiveData()
        }

        return isLoading
    }


    /*-----------------------------------------------Load data--------------------------------------------------------*/
    private fun loadWords() {
        isLoading.value = true

       wordDataSource.getWords(object : WordDataSource.LoadWordsDataSource {
           override fun onLoadWords(words: List<Word>) {
               this@RepeatWordViewModel.words.value = words
               isLoading.value = false
           }

           override fun onDataNotAvailable() {
               isLoading.value = false
           }

       })
    }


}