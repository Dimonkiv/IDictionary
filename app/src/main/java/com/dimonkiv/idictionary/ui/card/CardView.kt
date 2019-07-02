package com.dimonkiv.idictionary.ui.card

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.ui.MainActivity

class CardView(private val fragment: CardFragment,
               private val presenter: CardPresenter,
               private val activity: MainActivity,
               private val view: View,
               private val context: Context) : ICardContract.View {

    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView
    private lateinit var wordCountTV: TextView
    private lateinit var goodCountTV: TextView
    private lateinit var badCountTV: TextView


    init {
        initUI()
        initToolbar()
        presenter.setView(this)
    }

    private fun initUI() {
        toolbar = view.findViewById(R.id.toolbar)
        recyclerView = view.findViewById(R.id.recycler_view)
        wordCountTV = view.findViewById(R.id.word_count_tv)
        goodCountTV = view.findViewById(R.id.good_count_tv)
        badCountTV = view.findViewById(R.id.bad_count_tv)
    }

    private fun initToolbar() {
        activity.setSupportActionBar(toolbar)
        fragment.setHasOptionsMenu(true)

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener {
            presenter.onBackButtonClick()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_toolbar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?){
        when(item?.itemId) {

            R.id.search -> presenter.onSearchButtonClick()

            R.id.settings -> presenter.onSettingsButtonClick()
        }
    }

    override fun showTitle(title: String) {
        activity.supportActionBar?.title = "Назва колоди"
    }
}