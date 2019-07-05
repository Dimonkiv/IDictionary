package com.dimonkiv.idictionary.ui.newword

class NewWordPresenter(private val fragment: NewWordFragment): INewWordContract.Presenter {

    private lateinit var view: NewWordView
    private var isAddWordMode = false

    init {

    }

    override fun setView(view: NewWordView) {
        this.view = view
    }

    override fun onBackButtonClick() {
        if (isAddWordMode) {
            view.setSelectCardMode()
            isAddWordMode = false
            return
        }

        fragment.showPreviousFragment()
    }

    override fun onCardItemClick() {
        isAddWordMode = true
        view.setAddWordMode()

    }

    override fun onAddButtonClick() {

    }
}