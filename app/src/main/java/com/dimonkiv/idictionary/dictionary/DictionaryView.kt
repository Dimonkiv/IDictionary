package com.dimonkiv.idictionary.dictionary

import android.content.Context
import android.view.View
import com.dimonkiv.idictionary.MainActivity

class DictionaryView(private val fragment: DictionaryFragment,
                     private val presenter: DictionaryPresenter,
                     private val activity: MainActivity,
                     private val context: Context,
                     private val view: View) : IDictionaryContract.View {

    init {
        presenter.setView(this)
    }
}