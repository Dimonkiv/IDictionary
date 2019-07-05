package com.dimonkiv.idictionary.ui.wordgame

interface IWordGameContract {

    interface View

    interface Presenter {

        fun setView(view: WordGameView)
    }

    interface Fragment
}