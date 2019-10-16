package com.dimonkiv.idictionary.ui.modules.wordgame

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dimonkiv.idictionary.data.FirebaseManager
import com.dimonkiv.idictionary.data.models.Word
import com.dimonkiv.idictionary.utills.SingleLiveEvent

class WordGameViewModel : ViewModel() {
    private val _navigateToPreviousFragment = SingleLiveEvent<Any>()
    private val _showTranslatedWord = SingleLiveEvent<Any>()

    private lateinit var word: MutableLiveData<Word>


    /*--------------------------------------------------Get data------------------------------------------------------*/
    val navigateToPreviousFragment: MutableLiveData<Any>
        get() = _navigateToPreviousFragment

    val showTranslatedWord: MutableLiveData<Any>
        get() = _showTranslatedWord

    fun getWord(): MutableLiveData<Word> {
        if (!::word.isInitialized) {
            word = MutableLiveData()
            loadWords()
        }

        return word
    }

    private fun loadWords() {
        FirebaseManager.getInstance().getWordDataSource().getAll {words ->
            for (it in words) {
                word.postValue(it)
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