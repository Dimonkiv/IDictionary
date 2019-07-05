package com.dimonkiv.idictionary.ui.dictionary

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.dimonkiv.idictionary.FragmentById
import com.dimonkiv.idictionary.FragmentData
import com.dimonkiv.idictionary.ui.MainActivity
import com.dimonkiv.idictionary.R

class DictionaryFragment : Fragment(), IDictionaryContract.Fragment {
    private lateinit var root: View
    private lateinit var presenter: DictionaryPresenter
    private lateinit var view: DictionaryView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_dictionary, container, false)

        initPresenter()
        initView()

        return root
    }

    private fun initPresenter() {
        presenter = DictionaryPresenter(this)
    }

    private fun initView() {
        view = DictionaryView(this, presenter, getMainActivity(), context!!, root)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        view.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        view.onOptionsItemSelected(item)
        return super.onOptionsItemSelected(item)
    }

    override fun showCardFragment() {
        getMainActivity().changeFragment(FragmentData(FragmentById.CARD_FRAGMENT))
    }

    override fun showInputTypeFragment() {
        getMainActivity().changeFragment(FragmentData(FragmentById.INPUT_TYPE_DIALOG_FRAGMENT))
    }

    override fun showWordGameFragment() {
        getMainActivity().changeFragment(FragmentData(FragmentById.WORD_GAME_FRAGMENT))
    }

    private fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }
}