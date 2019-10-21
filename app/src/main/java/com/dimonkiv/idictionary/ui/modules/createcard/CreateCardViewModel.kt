package com.dimonkiv.idictionary.ui.modules.createcard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dimonkiv.idictionary.data.FirebaseManager
import com.dimonkiv.idictionary.data.models.Card
import com.dimonkiv.idictionary.utills.SingleLiveEvent

class CreateCardViewModel : ViewModel() {
    private val card = Card()

    private var _isLoading = MutableLiveData<Boolean>()
    private var _isShownError = SingleLiveEvent<Any>()
    private val _closeDialogs = SingleLiveEvent<Any>()


    /*-----------------------------------------------Get data---------------------------------------------------------*/
    val closeDialogs: LiveData<Any>
        get() = _closeDialogs

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val isShownError: LiveData<Any>
        get() = _isShownError


    /*-----------------------------------------------Listeners--------------------------------------------------------*/
    fun onCardTextChanged(card: String) {
        this.card.title = card
    }

    fun onAddButtonClick() {
        _isLoading.value = true

        if (card.title.isEmpty()) {
            _isShownError.call()
            _isLoading.value = false
            return
        }

        insertCard()
        _closeDialogs.call()
    }


    /*----------------------------------------------Work with data----------------------------------------------------*/
    private fun insertCard() {
        val cardDataSource = FirebaseManager.getInstance().getCardDataSource()
        cardDataSource.insert(card)
        _isLoading.value = false
    }
}