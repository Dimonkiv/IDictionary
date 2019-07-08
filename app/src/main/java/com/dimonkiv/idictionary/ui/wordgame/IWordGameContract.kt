package com.dimonkiv.idictionary.ui.wordgame

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem

interface IWordGameContract {

    interface View {
        fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?)

        fun onOptionsItemSelected(item: MenuItem?)

        fun showTitle()

        fun showTranslatedWord()
    }

    interface Presenter {

        fun setView(view: WordGameView)

        fun onBackButtonClick()

        fun onSettingsButtonClick()

        fun onShowTranslateButtonClick()
    }

    interface Fragment {

        fun showPreviousFragment()
    }
}