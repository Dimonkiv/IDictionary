package com.dimonkiv.idictionary.ui.newword

interface INewWordContract {

    interface View

    interface Presenter {

        fun setView(view: NewWordView)
    }

    interface Fragment
}