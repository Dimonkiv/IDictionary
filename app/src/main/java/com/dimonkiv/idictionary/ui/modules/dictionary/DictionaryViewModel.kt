package com.dimonkiv.idictionary.ui.modules.dictionary

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dimonkiv.idictionary.data.FirebaseManager
import com.dimonkiv.idictionary.data.models.Card

class DictionaryViewModel : ViewModel() {

    private lateinit var cards: MutableLiveData<List<Card>>
    private lateinit var isLoading: MutableLiveData<Boolean>


    /*------------------------------------------------------Get data--------------------------------------------------*/
    fun getCards(): MutableLiveData<List<Card>> {
        if (!::cards.isInitialized) {
            cards = MutableLiveData()
            loadData()
        }

        return cards
    }

    fun getIsLoading(): MutableLiveData<Boolean> {
        if (!::isLoading.isInitialized) {
            isLoading = MutableLiveData()
        }

        return isLoading
    }


    /*-----------------------------------------------------Load data--------------------------------------------------*/
    private fun loadData() {
        isLoading.value = true

        val cardDataSource = FirebaseManager.getInstance().getCardDataSource()

        cardDataSource.getAll {
            cards.postValue(it)
            isLoading.postValue(false)
        }
    }
}