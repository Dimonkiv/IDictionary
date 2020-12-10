package com.dimonkiv.idictionary.ui.modules.createcard

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.data.models.Card
import com.dimonkiv.idictionary.data.models.FragmentData
import com.dimonkiv.idictionary.ui.modules.MainActivity
import com.dimonkiv.idictionary.ui.modules.dictionary.DictionaryViewModel
import com.dimonkiv.idictionary.utills.FragmentById
import com.dimonkiv.idictionary.utills.obtainViewModel

class CreateCardFragment : Fragment() {

    private lateinit var root: View
    private lateinit var toolbar: Toolbar
    private lateinit var cardEditText: EditText
    private lateinit var addBtn: Button

    private lateinit var viewModel: CreateCardViewModel

    private val mainActivity: MainActivity
        get() = activity as MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_create_card, container, false)

        viewModel = obtainViewModel(CreateCardViewModel::class.java)
        initUI()
        subscribeUI()

        return root
    }

    private fun initUI() {
        toolbar = root.findViewById<Toolbar>(R.id.toolbar).apply {
            mainActivity.setSupportActionBar(this)
            mainActivity.supportActionBar?.title = "Створити колоду"
            setHasOptionsMenu(true)
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                mainActivity.changeFragment(FragmentData(FragmentById.BACK_FRAGMENT))
            }
        }

        cardEditText = root.findViewById<EditText>(R.id.card_et).apply {
            addTextChangedListener {
                viewModel.onCardTextChanged(it.toString())
            }
        }


        addBtn = root.findViewById<Button>(R.id.add_card_btn).apply {
            setOnClickListener {
                viewModel.onAddButtonClick()
            }
        }

    }

    private fun subscribeUI() {
        viewModel.isLoading.observe(this, {
            if(it) showProgressBar()
            else hideProgressBar()
        })

        viewModel.isShownError.observe(this, {
            mainActivity.showToast("Введіть назву колоди!")
        })

        viewModel.onSuccess.observe(this, {
            mainActivity.showToast("Колоду створено!")
            mainActivity.changeFragment(FragmentData(FragmentById.BACK_FRAGMENT))
        })
    }

    private fun showProgressBar() {
        mainActivity.showProgressBar()
    }

    private fun hideProgressBar() {
        mainActivity.hideProgressBar()
    }
}