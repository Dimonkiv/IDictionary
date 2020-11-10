package com.dimonkiv.idictionary.ui.modules.createcard

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dimonkiv.idictionary.utills.FragmentById
import com.dimonkiv.idictionary.data.models.FragmentData
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.ui.modules.MainActivity
import com.dimonkiv.idictionary.utills.obtainViewModel

class CreateCardFragment : Fragment() {
    private lateinit var root: View
    private lateinit var cardET: EditText
    private lateinit var backBtn: Button
    private lateinit var addBtn: Button

    private lateinit var viewModel: CreateCardViewModel


    /*-----------------------------------------------Initialization---------------------------------------------------*/
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_dialog_create_card, container, false)
        viewModel = obtainViewModel(CreateCardViewModel::class.java)

        initUI()
        setListeners()
        subscribeUI()

        return root
    }

    private fun initUI() {
        cardET = root.findViewById(R.id.card_et)
        backBtn = root.findViewById(R.id.back_btn)
        addBtn = root.findViewById(R.id.add_btn)
    }

    private fun setListeners() {
        cardET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onCardTextChanged(s.toString())
            }

        })

        backBtn.setOnClickListener {
            showPreviousFragment()
        }

        addBtn.setOnClickListener {
            viewModel.onAddButtonClick()
        }
    }

    private fun subscribeUI() {
        viewModel.isLoading.observe(this, Observer {
            if (it) showProgressBar()
            else hideProgressBar()
        })

        viewModel.isShownError.observe(this, Observer {
            showMessage(getString(R.string.card_error))
        })

        viewModel.closeDialogs.observe(this, Observer {
            closeDialogFragments()
        })
    }


    /*-----------------------------------------------Show/hide view---------------------------------------------------*/
    private fun showProgressBar() {
        getMainActivity().showProgressBar()
    }

    private fun hideProgressBar() {
        getMainActivity().hideProgressBar()
    }

    private fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


    /*------------------------------------------------Navigation------------------------------------------------------*/
    private fun showPreviousFragment() {
        getMainActivity().changeFragment(FragmentData(FragmentById.BACK_FRAGMENT))
    }

    private fun closeDialogFragments() {
        getMainActivity().changeFragment(FragmentData(FragmentById.CLOSE_DIALOG_FRAGMENT))
    }


    /*-----------------------------------------------Other methods----------------------------------------------------*/
    private fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }

}