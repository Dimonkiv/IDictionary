package com.dimonkiv.idictionary.ui.modules.dictionary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dimonkiv.idictionary.utills.FragmentById
import com.dimonkiv.idictionary.data.models.FragmentData
import com.dimonkiv.idictionary.ui.modules.MainActivity
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.data.models.Card
import com.dimonkiv.idictionary.ui.adapters.DictionaryAdapter
import com.dimonkiv.idictionary.ui.modules.card.CardFragment
import com.dimonkiv.idictionary.ui.modules.wordgame.WordGameFragment
import com.dimonkiv.idictionary.utills.RecyclerItemTouchHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DictionaryFragment : Fragment(), DictionaryAdapter.Callback {
    private lateinit var root: View
    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView
    private lateinit var addBtn: FloatingActionButton

    private lateinit var adapter: DictionaryAdapter
    private lateinit var viewModel: DictionaryViewModel


    /*------------------------------------------------Initialization--------------------------------------------------*/
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_dictionary, container, false)

        viewModel = ViewModelProviders.of(this).get(DictionaryViewModel::class.java)
        initUI()
        initToolbar()
        initAdapter()
        setListeners()
        subscribeUI()

        return root
    }

    private fun initUI() {
        toolbar = root.findViewById(R.id.toolbar)
        recyclerView = root.findViewById(R.id.recycler_view)
        addBtn = root.findViewById(R.id.add_fab)
    }

    private fun initToolbar() {
        getMainActivity().setSupportActionBar(toolbar)
        getMainActivity().supportActionBar?.title = context?.resources?.getString(R.string.dictionary)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_toolbar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.search -> {}

            R.id.settings -> {}
        }

        return super.onOptionsItemSelected(item)
    }

    private fun initAdapter() {
        adapter = DictionaryAdapter(this)

        ItemTouchHelper(RecyclerItemTouchHelper(adapter, recyclerView, context!!)).apply {
            attachToRecyclerView(recyclerView)
        }

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }


    /*---------------------------------------------Show initial data--------------------------------------------------*/
    private fun subscribeUI() {
        viewModel.getIsLoading().observe(this, Observer<Boolean> {
            if(it) showProgressBar()
            else hideProgressBar()
        })

        viewModel.getCards().observe(this, Observer<List<Card>> { adapter.setCardList(it!!) })
    }

    private fun showProgressBar() {
        getMainActivity().showProgressBar()
    }

    private fun hideProgressBar() {
        getMainActivity().hideProgressBar()
    }


    /*-----------------------------------------------Set listeners----------------------------------------------------*/
    private fun setListeners() {
        addBtn.setOnClickListener {
            getMainActivity().changeFragment(FragmentData(FragmentById.INPUT_TYPE_DIALOG_FRAGMENT))
        }
    }

    override fun onItemClick(cardId: String) {
        getMainActivity().changeFragment(FragmentData(FragmentById.CARD_FRAGMENT, CardFragment.getBundle(cardId)))
    }

    override fun onPlayButtonClick(cardId: String) {
        getMainActivity().changeFragment(FragmentData(FragmentById.WORD_GAME_FRAGMENT, WordGameFragment.getBundle(cardId)))
    }


    /*------------------------------------------------Other methods---------------------------------------------------*/
    private fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }
}