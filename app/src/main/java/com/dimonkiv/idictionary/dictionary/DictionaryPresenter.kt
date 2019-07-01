package com.dimonkiv.idictionary.dictionary

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

    }
}