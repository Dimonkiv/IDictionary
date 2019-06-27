package com.dimonkiv.idictionary.dictionary

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dimonkiv.idictionary.MainActivity
import com.dimonkiv.idictionary.R

class DictionaryFragment : Fragment(), IDictionaryContract.Fragment {
    private lateinit var root: View
    private lateinit var presenter: DictionaryPresenter
    private lateinit var view: DictionaryView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_dictionary, container, false)

        initPresenter()
        initView()

        return root
    }

    private fun initPresenter() {
        presenter = DictionaryPresenter(this)
    }

    private fun initView() {
        view = DictionaryView(this, presenter, getMainActivity(), context!!, root)
    }

    private fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }
}