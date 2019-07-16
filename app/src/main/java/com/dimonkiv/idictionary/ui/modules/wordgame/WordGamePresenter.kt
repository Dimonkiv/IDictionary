package com.dimonkiv.idictionary.ui.modules.wordgame

class WordGamePresenter(private val fragment: WordGameFragment):
    IWordGameContract.Presenter {

    private lateinit var view: WordGameView

    init {

    }

    override fun setView(view: WordGameView) {
        this.view = view
    }

    override fun onBackButtonClick() {
        fragment.showPreviousFragment()
    }

    override fun onShowTranslateButtonClick() {
        view.showTranslatedWord()
    }

    override fun onSettingsButtonClick() {

    }
}