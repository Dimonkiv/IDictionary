package com.dimonkiv.idictionary.ui.modules.card




class CardPresenter(private val fragment: CardFragment) :
    ICardContract.Presenter {

    private lateinit var view: CardView

    init {

    }

    override fun setView(view: CardView) {
        this.view = view
    }

    override fun onBackButtonClick() {
        fragment.showPreviousFragment()
    }

    override fun onSearchButtonClick() {

    }

    override fun onSettingsButtonClick() {

    }
}