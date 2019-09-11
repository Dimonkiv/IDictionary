package com.dimonkiv.idictionary.ui.modules.wordgame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.*
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dimonkiv.idictionary.utills.FragmentById
import com.dimonkiv.idictionary.data.models.FragmentData
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.data.models.Word
import com.dimonkiv.idictionary.ui.adapters.SwipeStackAdapter
import com.dimonkiv.idictionary.ui.modules.MainActivity
import link.fls.swipestack.SwipeStack

class WordGameFragment : Fragment() {


    private lateinit var root: View
    private lateinit var toolbar: Toolbar

    private val mainActivity: MainActivity
        get() = activity as MainActivity

    private lateinit var viewModel: WordGameViewModel
    private lateinit var swipeCard: SwipeStack


    /*-----------------------------------------------Initialization---------------------------------------------------*/
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_word_game, container, false)
        viewModel = ViewModelProviders.of(this).get(WordGameViewModel::class.java)

        initUI()
        setListeners()
        subscribeUI()

        return root
    }

    private fun initUI() {
        toolbar = root.findViewById<Toolbar>(R.id.toolbar).apply {
            mainActivity.setSupportActionBar(this)
            setHasOptionsMenu(true)
            setNavigationIcon(R.drawable.ic_arrow_back)
        }

        swipeCard = root.findViewById(R.id.swipe_card)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_toolbar_no_search, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.settings) {
            viewModel.onSettingsButtonClick()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun subscribeUI() {
        viewModel.navigateToPreviousFragment.observe(this, Observer { showPreviousFragment() })

        viewModel.getWords().observe(this, Observer { showWords(it) })
    }


    /*-------------------------------------------Set listeners--------------------------------------------------------*/
    private fun setListeners() {
        toolbar.setNavigationOnClickListener {
            viewModel.onBackButtonClick()
        }
    }


    /*---------------------------------------------Show initial data--------------------------------------------------*/
    private fun showTitle() {
        mainActivity.supportActionBar?.title = "Назва колоди"
    }

    private fun showWords(words: List<Word>) {
        swipeCard.adapter = SwipeStackAdapter(words)
    }


    /*--------------------------------------------Other methods-------------------------------------------------------*/
    private fun showPreviousFragment() {
        mainActivity.changeFragment(FragmentData(FragmentById.BACK_FRAGMENT))
    }

}
