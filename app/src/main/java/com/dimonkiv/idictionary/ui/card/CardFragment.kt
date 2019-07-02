package com.dimonkiv.idictionary.ui.card

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.ui.MainActivity

class CardFragment : Fragment(), ICardContract.Fragment {
    private lateinit var root: View
    private lateinit var presenter: CardPresenter
    private lateinit var view: CardView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_card, container, false)

        initPresenter()
        initView()

        return root
    }

    private fun initPresenter() {
        presenter = CardPresenter(this)
    }

    private fun initView() {
        view = CardView(this, presenter, getMainActivity(), root, context!!)
    }

    private fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }
}