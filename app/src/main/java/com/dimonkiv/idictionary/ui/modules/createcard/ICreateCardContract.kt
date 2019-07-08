package com.dimonkiv.idictionary.ui.modules.createcard

interface ICreateCardContract {

    interface View

    interface Presenter {

        fun setView(view: CreateCardView)

        fun onCardTextChanged(card: String)

        fun onBackButtonClick()

        fun onAddButtonClick()
    }

    interface Fragment {
        fun showPreviousFragment()
    }
}