package com.dimonkiv.idictionary.ui.createcard

interface ICreateCardContract {

    interface View

    interface Presenter {

        fun setView(view: CreateCardView)
    }

    interface Fragment
}