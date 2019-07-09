package com.dimonkiv.idictionary.ui.modules.dictionary

import android.content.Context
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.dimonkiv.idictionary.ui.adapters.DictionaryAdapter
import com.dimonkiv.idictionary.ui.modules.MainActivity
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.data.models.Card

class DictionaryView(private val fragment: DictionaryFragment,
                     private val presenter: DictionaryPresenter,
                     private val activity: MainActivity,
                     private val context: Context,
                     private val view: View) : IDictionaryContract.View, DictionaryAdapter.Callback {

    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView
    private lateinit var addBtn: FloatingActionButton

    private lateinit var adapter: DictionaryAdapter

    init {
        initUI()
        initToolbar()
        initAdapter()
        setListeners()
        presenter.setView(this)
    }

    private fun initUI() {
        toolbar = view.findViewById(R.id.toolbar)
        recyclerView = view.findViewById(R.id.recycler_view)
        addBtn = view.findViewById(R.id.add_fab)
    }

    private fun initToolbar() {
        activity.setSupportActionBar(toolbar)
        activity.supportActionBar?.title = context.resources.getString(R.string.dictionary)
        fragment.setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_toolbar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?) {
        when (item?.itemId) {
            R.id.search -> presenter.onSearchButtonClick()

            R.id.settings -> presenter.onSettingsButtonClick()
        }
    }

    private fun initAdapter() {
        adapter = DictionaryAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    private fun setListeners() {
        addBtn.setOnClickListener {
            presenter.onAddButtonClick()
        }
    }

    override fun onItemClick() {
        presenter.onItemClick()
    }

    override fun onPlayButtonClick() {
        presenter.onPlayButtonClick()
    }

    override fun showCardList(cardList: List<Card>) {
        adapter.setCardList(cardList)
    }
}