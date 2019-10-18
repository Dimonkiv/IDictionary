package com.dimonkiv.idictionary.ui.modules.wordgame

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dimonkiv.idictionary.data.FirebaseManager
import com.dimonkiv.idictionary.data.models.Card
import com.dimonkiv.idictionary.data.models.Word
import com.dimonkiv.idictionary.utills.SingleLiveEvent

class WordGameViewModel : ViewModel() {
    private val _navigateToPreviousFragment = SingleLiveEvent<Any>()

    private lateinit var words: MutableLiveData<List<Word>>
    private lateinit var card: MutableLiveData<Card>

    var cardId: String = ""


    /*--------------------------------------------------Get data------------------------------------------------------*/
    val navigateToPreviousFragment: MutableLiveData<Any>
        get() = _navigateToPreviousFragment

    fun getWords(): MutableLiveData<List<Word>> {
        if (!::words.isInitialized) {
            words = MutableLiveData()
            loadWords()
        }

        return words
    }

    fun getCard(): MutableLiveData<Card> {
        if (!::card.isInitialized) {
            card = MutableLiveData()
            loadCard()
        }

        return card
    }

    private fun loadWords() {
        FirebaseManager.getInstance().getWordDataSource().getAll {
            words.postValue(it)
        }
    }

    private fun loadCard() {
        FirebaseManager.getInstance().getCardDataSource().getById(cardId) {
            card.postValue(it)
        }
    }


    /*---------------------------------------------------Listeners----------------------------------------------------*/
    fun onBackButtonClick() {
        _navigateToPreviousFragment.call()
    }

    fun onSettingsButtonClick() {

    }
}