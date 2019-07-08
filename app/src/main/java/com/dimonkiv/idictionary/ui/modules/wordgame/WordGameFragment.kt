package com.dimonkiv.idictionary.ui.modules.wordgame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.*
import com.dimonkiv.idictionary.utills.FragmentById
import com.dimonkiv.idictionary.data.models.FragmentData
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.ui.modules.MainActivity

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
        view = WordGameView(
            this,
            presenter,
            getMainActivity(),
            context!!,
            root
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        view.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        view.onOptionsItemSelected(item)
        return super.onOptionsItemSelected(item)
    }

    override fun showPreviousFragment() {
        getMainActivity().changeFragment(FragmentData(FragmentById.BACK_FRAGMENT))
    }

    private fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }


}
