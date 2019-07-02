package com.dimonkiv.idictionary.ui.card

class CardPresenter(private val cardFragment: CardFragment) : ICardContract.Presenter {
    private lateinit var view: CardView

    init {

    }

    override fun setView(view: CardView) {
        this.view = view
    }
}