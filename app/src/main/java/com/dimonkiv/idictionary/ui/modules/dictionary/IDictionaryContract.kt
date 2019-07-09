package com.dimonkiv.idictionary.ui.modules.dictionary

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.dimonkiv.idictionary.data.models.Card

interface IDictionaryContract {

    interface View {
        fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?)

        fun onOptionsItemSelected(item: MenuItem?)

        fun showCardList(cardList: List<Card>)

        fun showProgressBar()

        fun hideProgressBar()
    }

    interface Presenter {

        fun setView(view: DictionaryView)

        fun onSearchButtonClick()

        fun onSettingsButtonClick()

        fun onAddButtonClick()

        fun onItemClick()

        fun onPlayButtonClick()
    }

    interface Fragment {
        fun showCardFragment()

        fun showInputTypeFragment()

        fun showWordGameFragment()
    }
}