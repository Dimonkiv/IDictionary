package com.dimonkiv.idictionary.ui.modules.dictionary

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dimonkiv.idictionary.data.models.Card
import com.dimonkiv.idictionary.data.source.CardDataSource

class DictionaryViewModel(
        private val cardDataSource: CardDataSource
) : ViewModel() {

    private lateinit var cards: MutableLiveData<List<Card>>
    private lateinit var isLoading: MutableLiveData<Boolean>

    var isMustRemoveCard = false


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

        cardDataSource.getCards(object : CardDataSource.LoadCardsDataSource {
            override fun onLoadCards(card: List<Card>) {
                cards.value = card
                isLoading.value = false
            }

            override fun onDataNotAvailable() {
                isLoading.value = false
            }

        })
    }

    fun removeCard(card: Card) {
        startWaitingRemoveTimer(card)
    }

    private fun startWaitingRemoveTimer(card: Card) {
        isMustRemoveCard = true
        Handler().postDelayed({
            /*if (isMustRemoveCard) {
                FirebaseManager.getInstance().getCardDataSource().removeCard(card.id)
            }*/
        }, 400)
    }
}