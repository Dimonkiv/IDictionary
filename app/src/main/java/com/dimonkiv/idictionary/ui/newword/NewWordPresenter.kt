package com.dimonkiv.idictionary.ui.newword

class NewWordPresenter(private val fragment: NewWordFragment): INewWordContract.Presenter {
    private lateinit var view: NewWordView

    init {

    }

    override fun setView(view: NewWordView) {
        this.view = view
    }
}