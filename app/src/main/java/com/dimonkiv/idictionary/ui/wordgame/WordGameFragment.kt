package com.dimonkiv.idictionary.ui.wordgame

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.ui.MainActivity

class WordGameFragment : Fragment(), IWordGameContract.Fragment {
    private lateinit var root: View
    private lateinit var presenter: WordGamePresenter
    private lateinit var view: WordGameView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_word_game, container, false)

        initPresenter()
        initView()

        return root
    }

    private fun initPresenter() {
        presenter = WordGamePresenter(this)
    }

    private fun initView() {
        view = WordGameView(this, presenter, getMainActivity(), context!!, root)
    }

    private fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }
}
