package com.dimonkiv.idictionary.ui.modules.newword

import com.dimonkiv.idictionary.data.models.Card

interface INewWordContract {

    interface View {
        fun setSelectCardMode()

        fun setAddWordMode()

        fun showProgressBar()

        fun hideProgressBar()

        fun showCardList(cardList: List<Card>)

        fun showMessage(message: String)

        fun clearFields()
    }

    interface Presenter {

        fun setView(view: NewWordView)

        fun onBackButtonClick()

        fun onAddButtonClick()

        fun onCardItemClick(id: String)

        fun onOriginalTextChanged(original: String)

        fun onTranslatedTextChanged(translated: String)
    }

    interface Fragment {
        fun showPreviousFragment()
    }
}