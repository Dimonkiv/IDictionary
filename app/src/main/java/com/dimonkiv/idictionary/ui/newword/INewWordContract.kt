package com.dimonkiv.idictionary.ui.newword

interface INewWordContract {

    interface View

    interface Presenter {

        fun setView(view: NewWordView)

        fun onBackButtonClick()

        fun onAddButtonClick()

        fun onCardItemClick()
    }

    interface Fragment {
        fun showPreviousFragment()
    }
}