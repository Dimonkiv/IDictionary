package com.dimonkiv.idictionary.data.source.implementation

import com.dimonkiv.idictionary.data.db.dao.CardDao
import com.dimonkiv.idictionary.data.models.Card
import com.dimonkiv.idictionary.data.source.CardDataSource
import com.dimonkiv.idictionary.utills.AppExecutors

class CardLocalDataSource private constructor(
        private val appExecutors: AppExecutors,
        private val cardDao: CardDao
) : CardDataSource {

    override fun getCards(callback: CardDataSource.LoadCardsDataSource) {
        appExecutors.discIO.execute {
            val cards = cardDao.getAll()
            appExecutors.mainThread.execute {
                if (cards.isEmpty())
                    callback.onDataNotAvailable()
                else
                    callback.onLoadCards(cards)
            }
        }
    }

    override fun getCard(id: String, callback: CardDataSource.LoadCardDataSource) {
        appExecutors.discIO.execute {
            val card = cardDao.getById(id)
            appExecutors.mainThread.execute {
                if (card == null)
                    callback.onDataNotAvailable()
                else
                    callback.onLoadCard(card)
            }
        }
    }

    override fun insertCard(card: Card) {
        appExecutors.discIO.execute {
            cardDao.insert(card)
        }
    }

    override fun updateCard(card: Card) {
        appExecutors.discIO.execute {
            cardDao.update(card)
        }
    }

    override fun removeCard(id: String) {
        appExecutors.discIO.execute {
            cardDao.remove(id)
        }
    }

    companion object {
        private var INSTANCE: CardLocalDataSource? = null

        @JvmStatic
        fun getInstance(appExecutors: AppExecutors, cardDao: CardDao): CardLocalDataSource {
            if (INSTANCE == null) {
                synchronized(CardLocalDataSource::class.java) {
                    INSTANCE = CardLocalDataSource(appExecutors, cardDao)
                }
            }

            return INSTANCE!!
        }
    }
}