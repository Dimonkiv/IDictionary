package com.dimonkiv.idictionary.ui.modules.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.MutableLiveData
import com.dimonkiv.idictionary.data.FirebaseManager
import com.dimonkiv.idictionary.data.models.Card
import com.dimonkiv.idictionary.data.models.Word


class CardViewModel(private val cardId: String) : ViewModel() {

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

        FirebaseManager.getInstance().getCardDataSource().getById(cardId) {
            card.postValue(it)
            isLoading.postValue(false)
        }
    }

    private fun loadWords() {
        isLoading.value = true

        FirebaseManager.getInstance().getWordDataSource().getAllByCardId(cardId) {
            words.postValue(it)
            isLoading.postValue(false)
        }
    }


    class CardViewModelFactory(private val cardId: String) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return (if (modelClass == CardViewModel::class.java) {
                CardViewModel(cardId) as T
            } else null)!!
        }
    }

}