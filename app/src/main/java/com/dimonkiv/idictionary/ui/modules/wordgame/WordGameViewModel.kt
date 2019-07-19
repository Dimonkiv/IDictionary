package com.dimonkiv.idictionary.ui.modules.wordgame

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dimonkiv.idictionary.utills.SingleLiveEvent

class WordGameViewModel : ViewModel() {
    private val _navigateToPreviousFragment = SingleLiveEvent<Any>()
    private val _showTranslatedWord = SingleLiveEvent<Any>()


    /*--------------------------------------------------Get data------------------------------------------------------*/
    val navigateToPreviousFragment: MutableLiveData<Any>
    get() = _navigateToPreviousFragment

    val showTranslatedWord: MutableLiveData<Any>
    get() = _showTranslatedWord


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