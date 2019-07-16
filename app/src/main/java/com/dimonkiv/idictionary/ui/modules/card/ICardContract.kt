package com.dimonkiv.idictionary.ui.modules.card

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.dimonkiv.idictionary.data.models.Word

interface ICardContract {

    interface View {
        fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?)

        fun onOptionsItemSelected(item: MenuItem?)

        fun showTitle(title: String)

        fun showWords(wordList: List<Word>)

        fun showProgressBar()

        fun hideProgressBar()
    }

    interface Presenter {

        fun setView(view: CardView)

        fun setCardId(cardId: String)

        fun onBackButtonClick()

        fun onSearchButtonClick()

        fun onSettingsButtonClick()
    }

    interface Fragment {
        fun showPreviousFragment()
    }
}