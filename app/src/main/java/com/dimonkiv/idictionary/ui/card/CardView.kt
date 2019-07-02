package com.dimonkiv.idictionary.ui.card

import android.content.Context
import android.view.View
import com.dimonkiv.idictionary.ui.MainActivity

class CardView(private val fragment: CardFragment,
               private val presenter: CardPresenter,
               private val activity: MainActivity,
               private val view: View,
               private val context: Context) : ICardContract.View {

    init {
        presenter.setView(this)
    }
}