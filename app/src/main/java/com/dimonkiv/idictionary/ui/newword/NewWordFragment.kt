package com.dimonkiv.idictionary.ui.newword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dimonkiv.idictionary.FragmentById
import com.dimonkiv.idictionary.FragmentData
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.ui.MainActivity

class NewWordFragment : Fragment(), INewWordContract.Fragment {
    private lateinit var root: View
    private lateinit var presenter: NewWordPresenter
    private lateinit var view: NewWordView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_dialog_new_word, container, false)

        initPresenter()
        initView()

        return root
    }

    private fun initPresenter() {
        presenter = NewWordPresenter(this)
    }

    private fun initView() {
        view = NewWordView(presenter, context!!, root)
    }

    override fun showPreviousFragment() {
        getMainActivity().changeFragment(FragmentData(FragmentById.BACK_FRAGMENT))
    }

    private fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }
}