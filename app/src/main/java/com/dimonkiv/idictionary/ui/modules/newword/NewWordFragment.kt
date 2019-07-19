package com.dimonkiv.idictionary.ui.modules.newword

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dimonkiv.idictionary.utills.FragmentById
import com.dimonkiv.idictionary.data.models.FragmentData
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.data.models.Card
import com.dimonkiv.idictionary.ui.adapters.SelectCardAdapter
import com.dimonkiv.idictionary.ui.modules.MainActivity

class NewWordFragment : Fragment(), SelectCardAdapter.Callback {
    private lateinit var root: View
    private lateinit var selectCardContainerLL: LinearLayout
    private lateinit var newWordContainerLL: LinearLayout
    private lateinit var englishET: EditText
    private lateinit var ukrainianET: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var backBtn: Button
    private lateinit var addBtn: Button

    private val adapter: SelectCardAdapter by lazy { SelectCardAdapter(this) }
    private lateinit var viewModel: NewWordViewModel

    private val mainActivity: MainActivity
        get() = activity as MainActivity


    /*------------------------------------------Initialization--------------------------------------------------------*/
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_dialog_new_word, container, false)
        viewModel = ViewModelProviders.of(this).get(NewWordViewModel::class.java)

        initUI()
        setListeners()
        subscribeUI()

        return root
    }

    private fun initUI() {
        selectCardContainerLL = root.findViewById(R.id.select_card_container_ll)
        newWordContainerLL = root.findViewById(R.id.new_word_container_ll)
        englishET = root.findViewById(R.id.english_et)
        ukrainianET = root.findViewById(R.id.ukrainian_et)
        recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapter
        }
        backBtn = root.findViewById(R.id.back_btn)
        addBtn = root.findViewById(R.id.add_btn)
    }

    private fun subscribeUI() {
        viewModel.isLoading.observe(this, Observer {
            if (it) showProgressBar()
            else hideProgressBar()
        })

        viewModel.isAddWordMode.observe(this, Observer {
            if (it) setAddWordMode()
            else setSelectCardMode()
        })

        viewModel.navigateToPreviousFragment.observe(this, Observer { showPreviousFragment() })

        viewModel.originalTextError.observe(this, Observer { showMessage(getString(R.string.original_text_error)) })

        viewModel.translatedTextError.observe(this, Observer { showMessage(getString(R.string.translated_text_error)) })

        viewModel.clearFields.observe(this, Observer { clearFields() })

        viewModel.getCards().observe(this, Observer { showCardList(it) })
    }


    /*------------------------------------------Set listeners---------------------------------------------------------*/
    private fun setListeners() {
        backBtn.setOnClickListener {
            viewModel.onBackButtonClick()
        }

        addBtn.setOnClickListener {
            viewModel.onAddButtonClick()
        }

        englishET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onOriginalTextChanged(s.toString())
            }

        })

        ukrainianET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onTranslatedTextChanged(s.toString())
            }

        })
    }

    override fun onSelectCard(id: String) {
        viewModel.onCardItemClick(id)
    }


    /*-------------------------------------------Show hide view-------------------------------------------------------*/
    private fun setSelectCardMode() {
        selectCardContainerLL.visibility = View.VISIBLE
        newWordContainerLL.visibility = View.GONE
        addBtn.visibility = View.INVISIBLE
        addBtn.isClickable = false
    }

    private fun setAddWordMode() {
        selectCardContainerLL.visibility = View.GONE
        newWordContainerLL.visibility = View.VISIBLE
        addBtn.visibility = View.VISIBLE
        addBtn.isClickable = true
    }

    private fun showProgressBar() {
        mainActivity.showProgressBar()
    }

    private fun hideProgressBar() {
        mainActivity.hideProgressBar()
    }


    /*-----------------------------------------------Show data--------------------------------------------------------*/
    private fun showCardList(cardList: List<Card>) {
        adapter.setItems(cardList)
    }

    private fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun clearFields() {
        englishET.setText("")
        ukrainianET.setText("")
    }


    /*----------------------------------------------Other methods-----------------------------------------------------*/
    private fun showPreviousFragment() {
        mainActivity.changeFragment(FragmentData(FragmentById.BACK_FRAGMENT))
    }

}