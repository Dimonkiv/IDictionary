package com.dimonkiv.idictionary.data.source

import com.dimonkiv.idictionary.data.models.Card

interface CardDataSource {

    interface LoadCardsDataSource {
        fun onLoadCards(card: List<Card>)

        fun onDataNotAvailable()
    }

    interface LoadCardDataSource {
        fun onLoadCard(card: Card)

        fun onDataNotAvailable()
    }

    fun getCards(callback: LoadCardsDataSource)

    fun getCard(id: String, callback: LoadCardDataSource)

    fun insertCard(card: Card)

    fun updateCard(card: Card)

    fun removeCard(id: String)
}