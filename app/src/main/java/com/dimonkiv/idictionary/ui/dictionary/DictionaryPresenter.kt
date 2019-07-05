package com.dimonkiv.idictionary.ui.dictionary

class DictionaryPresenter(private val fragment: DictionaryFragment) : IDictionaryContract.Presenter {


    private lateinit var view: DictionaryView

    init {

    }

    override fun setView(view: DictionaryView) {
        this.view = view
    }

    override fun onSearchButtonClick() {

    }

    override fun onSettingsButtonClick() {

    }

    override fun onAddButtonClick() {
        fragment.showInputTypeFragment()
    }

    override fun onPlayButtonClick() {
        fragment.showWordGameFragment()
    }

    override fun onItemClick() {
        fragment.showCardFragment()
    }
}