package com.dimonkiv.idictionary.ui.createcard

import android.content.Context
import android.view.View

class CreateCardView(private val presenter: CreateCardPresenter,
                     private val context: Context,
                     private val view: View) : ICreateCardContract.View {

    init {
        presenter.setView(this)
    }
}