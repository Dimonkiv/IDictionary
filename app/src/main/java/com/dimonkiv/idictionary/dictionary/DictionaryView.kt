package com.dimonkiv.idictionary.dictionary

import android.content.Context
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
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

    init {
        initUI()
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
}