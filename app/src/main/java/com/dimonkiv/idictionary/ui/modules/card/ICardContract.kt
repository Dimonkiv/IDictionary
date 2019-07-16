package com.dimonkiv.idictionary.ui.modules.card

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem

interface ICardContract {

    interface View {
        fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?)

        fun onOptionsItemSelected(item: MenuItem?)

        fun showTitle(title: String)
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