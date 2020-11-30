package com.dimonkiv.idictionary.ui.modules.create.addword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dimonkiv.idictionary.data.models.Card
import com.dimonkiv.idictionary.data.models.Word
import com.dimonkiv.idictionary.data.source.CardDataSource
import com.dimonkiv.idictionary.data.source.WordDataSource
import com.dimonkiv.idictionary.utills.SingleLiveEvent

class AddWordViewModel (
        private val cardDataSource: CardDataSource,
        private val wordDataSource: WordDataSource
) : ViewModel() {

    val word = Word()
    private lateinit var cards: List<Card>

    private lateinit var cardsArray: MutableLiveData<Array<String?>>
    private var _isLoading = MutableLiveData<Boolean>()
    private var _isCategoryEmpty = SingleLiveEvent<Any>()
    private var _isOriginalEmpty = SingleLiveEvent<Any>()
    private var _isTranslateEmpty = SingleLiveEvent<Any>()
    private var _isAddSuccessfully = SingleLiveEvent<Any>()

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val isCategoryEmpty: LiveData<Any>
        get() = _isCategoryEmpty

    val isOriginalEmpty: LiveData<Any>
        get() = _isOriginalEmpty

    val isTranslateEmpty: LiveData<Any>
        get() = _isTranslateEmpty

    val isAddSuccessfully: LiveData<Any>
        get() = _isAddSuccessfully

    fun getCards(): MutableLiveData<Array<String?>> {
        if (!::cardsArray.isInitialized) {
            cardsArray= MutableLiveData()
            loadData()
        }

        return cardsArray
    }


    private fun loadData() {
        _isLoading.value = true

        cardDataSource.getCards(object : CardDataSource.LoadCardsDataSource {
            override fun onLoadCards(card: List<Card>) {
                createCategoryNameArray(card)
                cards = card
                _isLoading.value = false
            }

            override fun onDataNotAvailable() {
                _isLoading.value = false
            }

        })
    }

    private fun createCategoryNameArray(list: List<Card>) {
        val cardArray = arrayOfNulls<String>(list.size)

        for (i in list.indices) {
            cardArray[i] = list[i].title
        }

        cardsArray.value = cardArray
    }

    fun onCategorySelected(i: Int) {
        word.cardId = cards[i].id
    }

    fun onAddButtonClick() {
        _isLoading.value = true

        if (word.original.isEmpty()) {
            _isOriginalEmpty.call()
            _isLoading.value = false
            return
        }

        if (word.translate.isEmpty()) {
            _isTranslateEmpty.call()
            _isLoading.value = false
            return
        }

        if (word.cardId.isEmpty()) {
            _isCategoryEmpty.call()
            _isLoading.value = false
            return
        }

        insertWord()
        _isAddSuccessfully.call()
    }

    private fun insertWord() {
        wordDataSource.insertWord(word)
        _isLoading.value = false
    }
}