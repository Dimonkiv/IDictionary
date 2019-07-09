package com.dimonkiv.idictionary.ui.modules.createcard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dimonkiv.idictionary.utills.FragmentById
import com.dimonkiv.idictionary.data.models.FragmentData
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.ui.modules.MainActivity

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
        view = CreateCardView(presenter, context!!, activity as MainActivity, root)
    }

    override fun showPreviousFragment() {
        (activity  as MainActivity).changeFragment(FragmentData(FragmentById.BACK_FRAGMENT))
    }

    override fun closeDialogFragments() {
        (activity as MainActivity).changeFragment(FragmentData(FragmentById.CLOSE_DIALOG_FRAGMENT))
    }

}