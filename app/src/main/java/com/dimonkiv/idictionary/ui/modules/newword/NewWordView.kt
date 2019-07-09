package com.dimonkiv.idictionary.ui.modules.newword

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.data.models.Card
import com.dimonkiv.idictionary.ui.adapters.SelectCardAdapter
import com.dimonkiv.idictionary.ui.modules.MainActivity

class NewWordView(private val presenter: NewWordPresenter,
                  private val context: Context,
                  private val activity: MainActivity,
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
                presenter.onOriginalTextChanged(s.toString())
            }

        })

        ukrainianET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.onTranslatedTextChanged(s.toString())
            }

        })
    }

    override fun onSelectCard(id: String) {
        presenter.onCardItemClick(id)
    }

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun clearFields() {
        englishET.setText("")
        ukrainianET.setText("")
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

    override fun showProgressBar() {
        activity.showProgressBar()
    }

    override fun hideProgressBar() {
        activity.hideProgressBar()
    }

    override fun showCardList(cardList: List<Card>) {
        adapter.setItems(cardList)
    }
}