package com.dimonkiv.idictionary.ui.modules.createcard

import com.dimonkiv.idictionary.data.FirebaseManager
import com.dimonkiv.idictionary.data.models.Card

class CreateCardPresenter(private val fragment: CreateCardFragment) : ICreateCardContract.Presenter {

    private lateinit var view: CreateCardView
    private val card = Card()

    init {

    }

    override fun setView(view: CreateCardView) {
        this.view = view
    }

    override fun onCardTextChanged(card: String) {
        this.card.title = card
    }

    override fun onBackButtonClick() {
        fragment.showPreviousFragment()
    }

    override fun onAddButtonClick() {
        view.showProgressBar()

        if (card.title.isEmpty())  {
            view.showMessage("Введіть назву колоди!")
            view.hideProgressBar()
            return
        }

        insertCard()
        fragment.closeDialogFragments()
    }

    private fun insertCard() {
        val cardDataSource = FirebaseManager.getInstance().getCardDataSource()
        cardDataSource.insert(card)
        view.hideProgressBar()
    }
}