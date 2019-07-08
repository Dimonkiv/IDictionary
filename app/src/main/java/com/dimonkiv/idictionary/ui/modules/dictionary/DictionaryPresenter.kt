package com.dimonkiv.idictionary.ui.modules.dictionary

import com.crashlytics.android.Crashlytics

class DictionaryPresenter(private val fragment: DictionaryFragment) :
    IDictionaryContract.Presenter {


    private lateinit var view: DictionaryView

    init {

    }

    override fun setView(view: DictionaryView) {
        this.view = view
    }

    override fun onSearchButtonClick() {

    }

    override fun onSettingsButtonClick() {
        Crashlytics.getInstance().crash()
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