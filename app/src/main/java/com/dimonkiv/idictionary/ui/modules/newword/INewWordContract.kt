package com.dimonkiv.idictionary.ui.modules.newword

interface INewWordContract {

    interface View {
        fun setSelectCardMode()

        fun setAddWordMode()
    }

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