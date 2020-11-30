package com.dimonkiv.idictionary.ui.modules.create.addword

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.data.models.FragmentData
import com.dimonkiv.idictionary.ui.modules.MainActivity
import com.dimonkiv.idictionary.utills.FragmentById
import com.dimonkiv.idictionary.utills.obtainViewModel

class AddWordFragment : Fragment() {
    private lateinit var root: View
    private lateinit var toolbar: Toolbar

    private lateinit var originalEt: EditText
    private lateinit var translateEt: EditText
    private lateinit var chooseCategoryBtn: Button
    private lateinit var addWordBtn: Button

    private lateinit var categoryDialog: AlertDialog

    private lateinit var viewModel: AddWordViewModel

    private val mainActivity: MainActivity
        get() = activity as MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_add_word, container, false)

        viewModel = obtainViewModel(AddWordViewModel::class.java)
        initUI()
        subscribeUI()
        return root
    }

    private fun initUI() {
        toolbar = root.findViewById<Toolbar>(R.id.toolbar).apply {
            mainActivity.setSupportActionBar(this)
            mainActivity.supportActionBar?.title = "Додати слово"
            setHasOptionsMenu(true)
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                mainActivity.changeFragment(FragmentData(FragmentById.BACK_FRAGMENT))
            }
        }

        originalEt = root.findViewById<EditText>(R.id.original_et).apply {
            addTextChangedListener {
                viewModel.word.original = it.toString()
            }
        }

        translateEt = root.findViewById<EditText>(R.id.translate_et).apply {
            addTextChangedListener {
                viewModel.word.translate = it.toString()
            }
        }

        chooseCategoryBtn = root.findViewById<Button>(R.id.chose_card_btn).apply {
            setOnClickListener {
                if (::categoryDialog.isInitialized)
                    categoryDialog.show()
            }
        }

        addWordBtn = root.findViewById<Button>(R.id.add_word_btn).apply {
            setOnClickListener {
                viewModel.onAddButtonClick()
            }
        }
    }

    private fun subscribeUI() {
        viewModel.isLoading.observe(this, {
            if (it) mainActivity.showProgressBar()
            else mainActivity.hideProgressBar()
        })

        viewModel.getCards().observe(this, {
            initCategoryDialog(it)
        })

        viewModel.isOriginalEmpty.observe(this, {
            mainActivity.showToast("Введіть оригінал слова!")
        })

        viewModel.isTranslateEmpty.observe(this, {
            mainActivity.showToast("Введіть переклад слова!")
        })

        viewModel.isCategoryEmpty.observe(this, {
            mainActivity.showToast("Виберіть категгорію!")
        })

        viewModel.isAddSuccessfully.observe(this, {
            mainActivity.showToast("Додано нове слово!")
        })
    }

    private fun initCategoryDialog(categories: Array<String?>) {
        val builder = AlertDialog.Builder(context!!)
                .setTitle("Виберіть категорію:")
                .setSingleChoiceItems(categories, 0) { dialog, which ->
                    viewModel.onCategorySelected(which)
                }
                .setPositiveButton("OK", null)
                .setNegativeButton("Cancel", null)

        categoryDialog = builder.create()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar_no_search, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.settings) {

        }
        return super.onOptionsItemSelected(item)
    }
}