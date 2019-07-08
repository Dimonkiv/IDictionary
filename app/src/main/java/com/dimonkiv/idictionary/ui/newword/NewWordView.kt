package com.dimonkiv.idictionary.ui.newword

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.adapters.SelectCardAdapter

class NewWordView(private val presenter: NewWordPresenter,
                  private val context: Context,
                  private val view: View):  INewWordContract.View, SelectCardAdapter.Callback {


    private lateinit var selectCardContainerLL: LinearLayout
    private lateinit var newWordContainerLL: LinearLayout
    private lateinit var englishET: EditText
    private lateinit var ukrainianET: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var backBtn: Button
    private lateinit var addBtn: Button

    private lateinit var adapter: SelectCardAdapter


    init {
        initUI()
        initAdapter()
        setListeners()
        presenter.setView(this)
    }

    private fun initUI() {
        selectCardContainerLL = view.findViewById(R.id.select_card_container_ll)
        newWordContainerLL = view.findViewById(R.id.new_word_container_ll)
        englishET = view.findViewById(R.id.english_et)
        ukrainianET = view.findViewById(R.id.ukrainian_et)
        recyclerView = view.findViewById(R.id.recycler_view)
        backBtn = view.findViewById(R.id.back_btn)
        addBtn = view.findViewById(R.id.add_btn)
    }

    private fun initAdapter() {
        adapter = SelectCardAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    private fun setListeners() {
        backBtn.setOnClickListener {
            presenter.onBackButtonClick()
        }

        addBtn.setOnClickListener {
            presenter.onAddButtonClick()
        }

        englishET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        ukrainianET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
    }

    override fun onSelectCard() {
        presenter.onCardItemClick()
    }

    override fun setSelectCardMode() {
        selectCardContainerLL.visibility = View.VISIBLE
        newWordContainerLL.visibility = View.GONE
        addBtn.visibility = View.INVISIBLE
        addBtn.isClickable = false
    }

    override fun setAddWordMode() {
        selectCardContainerLL.visibility = View.GONE
        newWordContainerLL.visibility = View.VISIBLE
        addBtn.visibility = View.VISIBLE
        addBtn.isClickable = true
    }
}