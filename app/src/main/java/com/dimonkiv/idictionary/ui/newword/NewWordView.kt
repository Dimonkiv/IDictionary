package com.dimonkiv.idictionary.ui.newword

import android.content.Context
import android.view.View

class NewWordView(private val presenter: NewWordPresenter,
                  private val context: Context,
                  private val view: View):  INewWordContract.View {


    init {
        presenter.setView(this)
    }
}