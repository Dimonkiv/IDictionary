package com.dimonkiv.idictionary.ui.modules.newword

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dimonkiv.idictionary.data.FirebaseManager
import com.dimonkiv.idictionary.data.models.Card
import com.dimonkiv.idictionary.data.models.Word
import com.dimonkiv.idictionary.utills.SingleLiveEvent

class NewWordViewModel : ViewModel() {
    private var _isAddWordMode = MutableLiveData<Boolean>()
    private lateinit var cards: MutableLiveData<List<Card>>
    private var _isLoading = MutableLiveData<Boolean>()

    private var _navigateToPreviousFragment = SingleLiveEvent<Any>()
    private var _originalTextError = SingleLiveEvent<Any>()
    private var _translatedTextError = SingleLiveEvent<Any>()
    private var _clearFields = SingleLiveEvent<Any>()

    private lateinit var word: Word


    /*---------------------------------------------------Get data-----------------------------------------------------*/
    val isAddWordMode: MutableLiveData<Boolean>
        get() = _isAddWordMode

    val isLoading: MutableLiveData<Boolean>
        get() = _isLoading

    val navigateToPreviousFragment: MutableLiveData<Any>
        get() = _navigateToPreviousFragment

    val originalTextError: MutableLiveData<Any>
        get() = _originalTextError

    val translatedTextError: MutableLiveData<Any>
        get() = _translatedTextError

    val clearFields: MutableLiveData<Any>
        get() = _clearFields

    fun getCards(): MutableLiveData<List<Card>> {
        if (!::cards.isInitialized) {
            cards = MutableLiveData()
            loadData()
        }

        return cards
    }


    /*-------------------------------------------Methods for work with data-------------------------------------------*/
    private fun loadData() {
        _isLoading.value = true

        FirebaseManager.getInstance().getCardDataSource().getAll {
            cards.postValue(it)
            _isLoading.postValue(false)
        }
    }

    private fun insertWord() {
        FirebaseManager.getInstance().getWordDataSource().insert(word)
    }


    /*---------------------------------------------------Listeners----------------------------------------------------*/
    fun onBackButtonClick() {
        if (_isAddWordMode.value!!) {
            _isAddWordMode.value = false
            return
        }

        _navigateToPreviousFragment.call()
    }

    fun onCardItemClick(id: String) {
        _isAddWordMode.value = true
        word.cardId = id
    }

    fun onOriginalTextChanged(original: String) {
        word.original = original
    }

    fun onTranslatedTextChanged(translated: String) {
        word.translated = translated
    }

    fun onAddButtonClick() {
        _isLoading.value = true

        if (word.original.isEmpty()) {
            _originalTextError.call()
            _isLoading.value = false
            return
        }

        if (word.translated.isEmpty()) {
            _translatedTextError.call()
            _isLoading.value = false
            return
        }

        insertWord()
        _clearFields.call()
        _isLoading.value = false
    }
}