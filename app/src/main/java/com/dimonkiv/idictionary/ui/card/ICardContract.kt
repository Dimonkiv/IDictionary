package com.dimonkiv.idictionary.ui.card

interface ICardContract {

    interface View

    interface Presenter {

        fun setView(view: CardView)
    }

    interface Fragment
}