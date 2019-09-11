package com.dimonkiv.idictionary.ui.modules.wordgame

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dimonkiv.idictionary.data.FirebaseManager
import com.dimonkiv.idictionary.data.models.Word
import com.dimonkiv.idictionary.utills.SingleLiveEvent

class WordGameViewModel : ViewModel() {
    private val _navigateToPreviousFragment = SingleLiveEvent<Any>()
    private val _showTranslatedWord = SingleLiveEvent<Any>()

    private lateinit var words: MutableLiveData<List<Word>>


    /*--------------------------------------------------Get data------------------------------------------------------*/
    val navigateToPreviousFragment: MutableLiveData<Any>
    get() = _navigateToPreviousFragment

    val showTranslatedWord: MutableLiveData<Any>
    get() = _showTranslatedWord

    fun getWords(): MutableLiveData<List<Word>> {
        if (!::words.isInitialized) {
            words = MutableLiveData()
            loadWords()
        }

        return words
    }

    private fun loadWords() {
        FirebaseManager.getInstance().getWordDataSource().apply {
            getAll {
                words.postValue(it)
            }
        }
    }


    /*---------------------------------------------------Listeners----------------------------------------------------*/
    fun onBackButtonClick() {
        _navigateToPreviousFragment.call()
    }

    fun onShowTranslateButtonClick() {
        _showTranslatedWord.call()
    }

    fun onSettingsButtonClick() {

    }
}