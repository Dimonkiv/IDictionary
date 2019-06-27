package com.dimonkiv.idictionary.dictionary

interface IDictionaryContract {

    interface View

    interface Presenter {

        fun setView(view: DictionaryView)
    }

    interface Fragment
}