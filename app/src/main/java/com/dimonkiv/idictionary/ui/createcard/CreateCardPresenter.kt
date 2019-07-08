package com.dimonkiv.idictionary.ui.createcard

class CreateCardPresenter(private val fragment: CreateCardFragment) : ICreateCardContract.Presenter {

    private lateinit var view: CreateCardView

    init {

    }

    override fun setView(view: CreateCardView) {
        this.view = view
    }

    override fun onCardTextChanged(card: String) {

    }

    override fun onBackButtonClick() {
        fragment.showPreviousFragment()
    }

    override fun onAddButtonClick() {

    }
}