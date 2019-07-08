package com.dimonkiv.idictionary.ui.modules.wordgame

import android.content.Context
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.ui.modules.MainActivity

class WordGameView(private val fragment: WordGameFragment,
                   private val presenter: WordGamePresenter,
                   private val activity: MainActivity,
                   private val context: Context,
                   private val view: View): IWordGameContract.View {


    private lateinit var toolbar: Toolbar
    private lateinit var originalTV: TextView
    private lateinit var translatedTV: TextView
    private lateinit var showTranslateRL: RelativeLayout

    init {
        initUI()
        initToolbar()
        setListeners()
        showTitle()
        presenter.setView(this)
    }

    private fun initUI() {
        toolbar = view.findViewById(R.id.toolbar)
        originalTV = view.findViewById(R.id.original_tv)
        translatedTV = view.findViewById(R.id.translated_tv)
        showTranslateRL = view.findViewById(R.id.show_translate_rl)
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
        inflater?.inflate(R.menu.menu_toolbar_no_search, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?) {
        if (item?.itemId == R.id.settings) {
            presenter.onSettingsButtonClick()
        }
    }

    private fun setListeners() {
        showTranslateRL.setOnClickListener {
            presenter.onShowTranslateButtonClick()
        }
    }

    override fun showTranslatedWord() {
        showTranslateRL.visibility = View.GONE
        translatedTV.visibility = View.VISIBLE
    }

    override fun showTitle() {
        activity.supportActionBar?.title = "Назва колоди"
    }
}