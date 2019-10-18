package com.dimonkiv.idictionary.ui.modules.wordgame

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.*
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import com.dimonkiv.idictionary.utills.FragmentById
import com.dimonkiv.idictionary.data.models.FragmentData
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.data.models.Word
import com.dimonkiv.idictionary.ui.adapters.CardStackAdapter
import com.dimonkiv.idictionary.ui.modules.MainActivity
import com.yuyakaido.android.cardstackview.*

class WordGameFragment : Fragment(), CardStackListener {
    private lateinit var root: View
    private lateinit var toolbar: Toolbar

    private val cardStackView by lazy { root.findViewById<CardStackView>(R.id.card_stack_view) }
    private val cardManager by lazy {CardStackLayoutManager(context!!, this)}
    private val cardAdapter by lazy { CardStackAdapter() }

    private val mainActivity: MainActivity
        get() = activity as MainActivity

    private lateinit var viewModel: WordGameViewModel

    companion object {
        private const val CARD_ID = "cardId"

        fun getBundle(cardId: String): Bundle {
            return Bundle().apply {
                putString(CARD_ID, cardId)
            }
        }
    }


    /*-----------------------------------------------Initialization---------------------------------------------------*/
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_word_game, container, false)
        viewModel = ViewModelProviders.of(this).get(WordGameViewModel::class.java)

        initUI()
        resumeBundle()
        initCardStack()
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
    }

    private fun initCardStack(){
        cardManager.setStackFrom(StackFrom.Top)
        cardManager.setDirections(Direction.HORIZONTAL)
        cardManager.setCanScrollHorizontal(true)
        cardManager.setCanScrollVertical(false)
        cardManager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        cardManager.setOverlayInterpolator(LinearInterpolator())
        cardStackView.layoutManager = cardManager
        cardStackView.adapter = cardAdapter
        cardStackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
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

    private fun resumeBundle() {
        if (arguments!!.containsKey(CARD_ID)) {
            viewModel.cardId = arguments!!.getString(CARD_ID)!!
        }
    }

    private fun subscribeUI() {
        viewModel.navigateToPreviousFragment.observe(this, Observer { showPreviousFragment() })

        viewModel.getWords().observe(this, Observer { showWords(it) })

        viewModel.getCard().observe(this, Observer { showTitle(it.title) })
    }


    /*-------------------------------------------Set listeners--------------------------------------------------------*/
    private fun setListeners() {
        toolbar.setNavigationOnClickListener {
            viewModel.onBackButtonClick()
        }
    }

    override fun onCardDisappeared(view: View?, position: Int) {
        val textView = view!!.findViewById<TextView>(R.id.original_tv)
        Log.d("CardStackView", "onCardDisappeared: ($position) ${textView.text}")
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
        Log.d("CardStackView", "onCardDragging: d = ${direction!!.name}, r = $ratio")
    }

    override fun onCardSwiped(direction: Direction?) {
        Log.d("CardStackView", "onCardSwiped: p = ${cardManager.topPosition}, d = $direction")

    }

    override fun onCardCanceled() {
        Log.d("CardStackView", "onCardCanceled: ${cardManager.topPosition}")
    }

    override fun onCardAppeared(view: View?, position: Int) {
        val textView = view!!.findViewById<TextView>(R.id.original_tv)
    }

    override fun onCardRewound() {
        Log.d("CardStackView", "onCardRewound: ${cardManager.topPosition}")
    }


    /*---------------------------------------------Show initial data--------------------------------------------------*/
    private fun showTitle(title: String) {
        mainActivity.supportActionBar?.title = title
    }

    private fun showWords(words: List<Word>) {
        cardAdapter.setWords(words)
    }


    /*--------------------------------------------Other methods-------------------------------------------------------*/
    private fun showPreviousFragment() {
        mainActivity.changeFragment(FragmentData(FragmentById.BACK_FRAGMENT))
    }

}
