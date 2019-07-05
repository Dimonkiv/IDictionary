package com.dimonkiv.idictionary.ui.wordgame

class WordGamePresenter(private val fragment: WordGameFragment): IWordGameContract.Presenter {
    private lateinit var view: WordGameView

    init {

    }

    override fun setView(view: WordGameView) {
        this.view = view
    }
}