package com.dimonkiv.idictionary.ui.modules.card

import com.dimonkiv.idictionary.data.FirebaseManager
import com.dimonkiv.idictionary.data.models.Word


class CardPresenter(private val fragment: CardFragment) :
    ICardContract.Presenter {

    private lateinit var view: CardView
    private lateinit var cardId: String

    private val wordList = ArrayList<Word>()

    init {

    }

    override fun setView(view: CardView) {
        this.view = view
    }

    override fun setCardId(cardId: String) {
        this.cardId = cardId
        loadData()
    }

    private fun loadData() {
        FirebaseManager.getInstance().let {
            loadWords(it)
            loadCard(it)
        }
    }

    private fun loadCard(manager: FirebaseManager) {
        view.showProgressBar()

        manager.getCardDataSource().getById(cardId) {
            view.showTitle(it.title)
            view.hideProgressBar()
        }
    }

    private fun loadWords(manager: FirebaseManager) {
        view.showProgressBar()

        manager.getWordDataSource().getAllByCardId(cardId) {
            this.wordList.clear()
            this.wordList.addAll(it)
            view.showWords(wordList)
            view.hideProgressBar()
        }
    }

    override fun onBackButtonClick() {
        fragment.showPreviousFragment()
    }

    override fun onSearchButtonClick() {

    }

    override fun onSettingsButtonClick() {

    }
}