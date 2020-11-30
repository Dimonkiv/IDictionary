package com.dimonkiv.idictionary.ui.modules.dictionary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.*
import android.widget.RelativeLayout
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
import com.dimonkiv.idictionary.utills.obtainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_create.*

class DictionaryFragment : Fragment(), DictionaryAdapter.Callback {
    private lateinit var root: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var addCardRL: RelativeLayout

    private lateinit var adapter: DictionaryAdapter
    private lateinit var viewModel: DictionaryViewModel



    /*------------------------------------------------Initialization--------------------------------------------------*/
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_dictionary, container, false)

        viewModel = obtainViewModel(DictionaryViewModel::class.java)
        initUI()
        initAdapter()
        setListeners()
        subscribeUI()

        return root
    }

    private fun initUI() {
        recyclerView = root.findViewById(R.id.recycler_view)
        addCardRL = root.findViewById(R.id.add_container_rl)
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
        addCardRL.setOnClickListener{
            (activity as MainActivity).changeFragment(FragmentData(FragmentById.CREATE_CARD_FRAGMENT))
        }
    }

    override fun onItemClick(cardId: String) {
        getMainActivity().changeFragment(FragmentData(FragmentById.CARD_FRAGMENT, CardFragment.getBundle(cardId)))
    }

    override fun onRemoveItem(card: Card) {
        viewModel.removeCard(card)
    }

    override fun onCheckboxChangeState(card: Card, isChecked: Boolean) {

    }

    override fun undoRemove() {
        viewModel.isMustRemoveCard = false
    }


    /*------------------------------------------------Other methods---------------------------------------------------*/
    private fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }
}