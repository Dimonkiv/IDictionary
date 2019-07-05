package com.dimonkiv.idictionary.ui.newword

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.dimonkiv.idictionary.R

class NewWordView(private val presenter: NewWordPresenter,
                  private val context: Context,
                  private val view: View):  INewWordContract.View {

    private lateinit var selectCardContainerLL: LinearLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var backBtn: Button
    private lateinit var addBtn: Button

    init {
        initUI()
        setListeners()
        presenter.setView(this)
    }

    private fun initUI() {
        selectCardContainerLL = view.findViewById(R.id.select_card_container_ll)
        recyclerView = view.findViewById(R.id.recycler_view)
        backBtn = view.findViewById(R.id.back_btn)
        addBtn = view.findViewById(R.id.add_btn)
    }

    private fun setListeners() {
        backBtn.setOnClickListener {
            presenter.onBackButtonClick()
        }

        addBtn.setOnClickListener {
            presenter.onAddButtonClick()
        }
    }
}