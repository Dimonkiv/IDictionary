package com.dimonkiv.idictionary.ui.createcard

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dimonkiv.idictionary.R

class CreateCardFragment : Fragment(), ICreateCardContract.Fragment {
    private lateinit var root: View
    private lateinit var presenter: CreateCardPresenter
    private lateinit var view: CreateCardView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_dialog_create_card, container, false)

        initPresenter()
        initView()

        return root
    }

    private fun initPresenter() {
        presenter = CreateCardPresenter(this)
    }

    private fun initView() {
        view = CreateCardView(presenter, context!!, root)
    }


}