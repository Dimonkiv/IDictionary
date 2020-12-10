package com.dimonkiv.idictionary.ui.modules.words

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.data.models.Card
import com.dimonkiv.idictionary.data.models.FragmentData
import com.dimonkiv.idictionary.ui.adapters.DictionaryAdapter
import com.dimonkiv.idictionary.ui.adapters.WordsAdapter
import com.dimonkiv.idictionary.ui.modules.MainActivity
import com.dimonkiv.idictionary.ui.modules.dictionary.DictionaryViewModel
import com.dimonkiv.idictionary.utills.FragmentById
import com.dimonkiv.idictionary.utills.RecyclerItemTouchHelper
import com.dimonkiv.idictionary.utills.ViewModelFactory
import com.dimonkiv.idictionary.utills.obtainViewModel

class WordsFragment : Fragment() {

    private lateinit var root: View
    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: WordsAdapter
    private lateinit var viewModel: WordsViewModel

    private val mainActivity: MainActivity
        get() = activity as MainActivity

    companion object {
        private const val CARD = "card"

        fun getBundle(card: Card): Bundle {
            val bundle = Bundle()
            bundle.putSerializable(CARD, card)

            return bundle
        }
    }


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_words, container, false)
        viewModel = obtainViewModel(WordsViewModel::class.java)
        initUI()
        initAdapter()
        resumeBundle()
        subscribeUI()
        return root
    }

    private fun initUI() {
        toolbar = root.findViewById<Toolbar>(R.id.toolbar).apply {
            mainActivity.setSupportActionBar(this)
            setHasOptionsMenu(true)
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                mainActivity.changeFragment(FragmentData(FragmentById.BACK_FRAGMENT))
            }
        }

        recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view)
    }
    private fun initAdapter() {
        adapter = WordsAdapter()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }


    private fun resumeBundle() {
        if (requireArguments().containsKey(CARD)) {
            val card =  requireArguments().getSerializable(CARD) as Card
            viewModel.card = card
            mainActivity.supportActionBar?.title = card.title
        }
    }

    private fun subscribeUI() {
        viewModel.getWords().observe(this) {
            adapter.setWordList(it)
        }
    }
}