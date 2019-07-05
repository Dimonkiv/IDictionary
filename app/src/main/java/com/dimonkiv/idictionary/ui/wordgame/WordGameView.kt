package com.dimonkiv.idictionary.ui.wordgame

import android.content.Context
import android.view.View
import com.dimonkiv.idictionary.ui.MainActivity

class WordGameView(private val fragment: WordGameFragment,
                   private val presenter: WordGamePresenter,
                   private val activity: MainActivity,
                   private val context: Context,
                   private val view: View): IWordGameContract.View {

    init {
        presenter.setView(this)
    }
}