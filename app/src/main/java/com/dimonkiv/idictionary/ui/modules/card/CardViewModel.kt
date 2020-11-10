package com.dimonkiv.idictionary.ui.modules.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.MutableLiveData
import com.dimonkiv.idictionary.data.models.Card
import com.dimonkiv.idictionary.data.models.Word
import com.dimonkiv.idictionary.data.source.CardDataSource


class CardViewModel(private val cardDataSource: CardDataSource) : ViewModel() {

    private lateinit var words: MutableLiveData<List<Word>>
    private lateinit var card: MutableLiveData<Card>
    private lateinit var isLoading: MutableLiveData<Boolean>


    /*--------------------------------------------------Get data------------------------------------------------------*/
    fun getCard(): MutableLiveData<Card> {
        if (!::card.isInitialized) {
            card = MutableLiveData()
            loadCard()
        }

        return card
    }

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
    private fun loadCard() {
        isLoading.value = true

        /*FirebaseManager.getInstance().getCardDataSource().getById(cardId) {
            card.postValue(it)
            isLoading.postValue(false)
        }*/
    }

    private fun loadWords() {
        isLoading.value = true

        /*FirebaseManager.getInstance().getWordDataSource().getAllByCardId(cardId) {
            words.postValue(it)
            isLoading.postValue(false)
        }*/
    }


}