package com.dimonkiv.idictionary.ui.modules.dictionary

import com.crashlytics.android.Crashlytics
import com.dimonkiv.idictionary.data.FirebaseManager
import com.dimonkiv.idictionary.data.models.Card

class DictionaryPresenter(private val fragment: DictionaryFragment) :
    IDictionaryContract.Presenter {


    private lateinit var view: DictionaryView
    private val cardList = ArrayList<Card>()


    override fun setView(view: DictionaryView) {
        this.view = view
        loadData()
    }

    private fun loadData() {
        view.showProgressBar()
        val cardDataSource = FirebaseManager.getInstance().getCardDataSource()
        cardDataSource.getAll {
            this.cardList.clear()
            this.cardList.addAll(it)
            showCardList()
            view.hideProgressBar()
        }
    }

    private fun showCardList() {
        view.showCardList(cardList)
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