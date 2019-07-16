package com.dimonkiv.idictionary.ui.modules.createcard

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.ui.modules.MainActivity

class CreateCardView(private val presenter: CreateCardPresenter,
                     private val context: Context,
                     private val activity: MainActivity,
                     private val view: View) : ICreateCardContract.View {

    private lateinit var cardET: EditText
    private lateinit var backBtn: Button
    private lateinit var addBtn: Button

    init {
        initUI()
        setListeners()
        presenter.setView(this)
    }

    private fun initUI() {
        cardET = view.findViewById(R.id.card_et)
        backBtn = view.findViewById(R.id.back_btn)
        addBtn = view.findViewById(R.id.add_btn)
    }

    private fun setListeners() {
        cardET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.onCardTextChanged(s.toString())
            }

        })

        backBtn.setOnClickListener {
            presenter.onBackButtonClick()
        }

        addBtn.setOnClickListener {
            presenter.onAddButtonClick()
        }
    }

    override fun showProgressBar() {
        activity.showProgressBar()
    }

    override fun hideProgressBar() {
        activity.hideProgressBar()
    }

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}