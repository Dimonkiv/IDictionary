package com.dimonkiv.idictionary.dictionary

import android.content.Context
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.dimonkiv.idictionary.DictionaryAdapter
import com.dimonkiv.idictionary.MainActivity
import com.dimonkiv.idictionary.R

class DictionaryView(private val fragment: DictionaryFragment,
                     private val presenter: DictionaryPresenter,
                     private val activity: MainActivity,
                     private val context: Context,
                     private val view: View) : IDictionaryContract.View {

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
        adapter = DictionaryAdapter()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    private fun setListeners() {
        addBtn.setOnClickListener {
            presenter.onAddButtonClick()
        }
    }
}