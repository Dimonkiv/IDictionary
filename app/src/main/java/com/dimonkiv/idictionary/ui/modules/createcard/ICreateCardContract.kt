package com.dimonkiv.idictionary.ui.modules.createcard

interface ICreateCardContract {

    interface View {
        fun showMessage(message: String)

        fun showProgressBar()

        fun hideProgressBar()
    }

    interface Presenter {

        fun setView(view: CreateCardView)

        fun onCardTextChanged(card: String)

        fun onBackButtonClick()

        fun onAddButtonClick()
    }

    interface Fragment {
        fun showPreviousFragment()

        fun closeDialogFragments()
    }
}