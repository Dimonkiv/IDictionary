package com.dimonkiv.idictionary.ui.newword

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dimonkiv.idictionary.R

class NewWordFragment : Fragment(), INewWordContract.Fragment {
    private lateinit var root: View
    private lateinit var presenter: NewWordPresenter
    private lateinit var view: NewWordView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_dialog_new_word, container, false)

        return root
    }
}