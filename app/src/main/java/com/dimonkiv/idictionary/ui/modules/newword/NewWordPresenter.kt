package com.dimonkiv.idictionary.ui.modules.newword

import com.dimonkiv.idictionary.data.FirebaseManager
import com.dimonkiv.idictionary.data.models.Card
import com.dimonkiv.idictionary.data.models.Word

class NewWordPresenter(private val fragment: NewWordFragment):
    INewWordContract.Presenter {

    private lateinit var view: NewWordView
    private var isAddWordMode = false
    private val word = Word()

    init {

    }

    override fun setView(view: NewWordView) {
        this.view = view
        loadData()
    }

    private fun loadData()  {
        view.showProgressBar()
        val cardDataSource = FirebaseManager.getInstance().getCardDataSource()
        cardDataSource.getAll {
            showCardList(it)
            view.hideProgressBar()
        }
    }

    private fun insertWord() {
        val wordDataSource = FirebaseManager.getInstance().getWordDataSource()
        wordDataSource.insert(word)
    }

    private fun showCardList(cardList: List<Card>) {
        view.showCardList(cardList)
    }

    override fun onBackButtonClick() {
        if (isAddWordMode) {
            view.setSelectCardMode()
            isAddWordMode = false
            return
        }

        fragment.showPreviousFragment()
    }

    override fun onCardItemClick(id: String) {
        isAddWordMode = true
        view.setAddWordMode()
        word.cardId = id
    }

    override fun onOriginalTextChanged(original: String) {
        word.original = original
    }

    override fun onTranslatedTextChanged(translated: String) {
        word.translated = translated
    }

    override fun onAddButtonClick() {
        view.showProgressBar()

        if(word.original.isEmpty()) {
            view.showMessage("Введіть оригінальне слово!")
            view.hideProgressBar()
            return
        }

        if (word.translated.isEmpty()) {
            view.showMessage("Введіть переклад!")
            view.hideProgressBar()
            return
        }

        insertWord()
        view.clearFields()
        view.hideProgressBar()
    }
}