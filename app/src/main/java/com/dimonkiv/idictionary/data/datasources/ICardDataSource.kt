package com.dimonkiv.idictionary.data.datasources

import com.dimonkiv.idictionary.data.models.Card

interface ICardDataSource {

    fun insert(card: Card)

    fun getAll(onResult: (List<Card>) -> Unit)

    fun getById(cardId: String, onResult: (Card) -> Unit)
}