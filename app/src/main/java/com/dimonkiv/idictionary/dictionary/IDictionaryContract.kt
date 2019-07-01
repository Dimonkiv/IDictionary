package com.dimonkiv.idictionary.dictionary

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem

interface IDictionaryContract {

    interface View {
        fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?)

        fun onOptionsItemSelected(item: MenuItem?)
    }

    interface Presenter {

        fun setView(view: DictionaryView)

        fun onSearchButtonClick()

        fun onSettingsButtonClick()

        fun onAddButtonClick()
    }

    interface Fragment
}