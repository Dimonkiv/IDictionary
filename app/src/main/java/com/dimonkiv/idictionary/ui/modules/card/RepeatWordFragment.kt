package com.dimonkiv.idictionary.ui.modules.card

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.*
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.dimonkiv.idictionary.utills.FragmentById
import com.dimonkiv.idictionary.data.models.FragmentData
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.data.models.Card
import com.dimonkiv.idictionary.data.models.Word
import com.dimonkiv.idictionary.ui.adapters.RepeatWordAdapter
import com.dimonkiv.idictionary.ui.modules.MainActivity
import com.dimonkiv.idictionary.utills.obtainViewModel

class RepeatWordFragment : Fragment(){

    private lateinit var root: View
    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var wordCountTV: TextView
    private lateinit var goodCountTV: TextView
    private lateinit var badCountTV: TextView

    private lateinit var adapter: RepeatWordAdapter
    private lateinit var viewModel: RepeatWordViewModel

    companion object {
        private const val CARD_ID = "cardId"

        fun getBundle(cardId: String): Bundle {
            val bundle = Bundle()
            bundle.putString(CARD_ID, cardId)

            return bundle
        }
    }


    /*--------------------------------------------------Initialization------------------------------------------------*/
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_card, container, false)

        viewModel = obtainViewModel(RepeatWordViewModel::class.java)
        initUI()
        initToolbar()
        initAdapter()
        subscribeUI()

        return root
    }

    private fun initUI() {
        toolbar = root.findViewById(R.id.toolbar)
        recyclerView = root.findViewById(R.id.recycler_view)
        wordCountTV = root.findViewById(R.id.word_count_tv)
        goodCountTV = root.findViewById(R.id.good_count_tv)
        badCountTV = root.findViewById(R.id.bad_count_tv)
    }

    private fun initToolbar() {
        getMainActivity().setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        getMainActivity().supportActionBar?.title = "Повторити вивчені слова"

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener {
            showPreviousFragment()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {

            R.id.search -> {}

            R.id.settings -> {}
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initAdapter() {
        adapter = RepeatWordAdapter()

        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }


    /*-----------------------------------------------Show initial data------------------------------------------------*/
    private fun subscribeUI() {
        viewModel.getIsLoading().observe(this, {
            if(it) showProgressBar()
            else hideProgressBar()
        })

        viewModel.getWords().observe(this, { showWords(it) })
    }

    private fun showTitle(title: String) {
        getMainActivity().supportActionBar?.title = title
    }

    private fun showWords(wordList: List<Word>) {
        adapter.setWordList(wordList)
    }

    private fun showProgressBar() {
        getMainActivity().showProgressBar()
    }

    private fun hideProgressBar() {
        getMainActivity().hideProgressBar()
    }


    /*------------------------------------------------Other methods---------------------------------------------------*/
    private fun showPreviousFragment() {
        getMainActivity().changeFragment(FragmentData(FragmentById.BACK_FRAGMENT))
    }

    private fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }
}