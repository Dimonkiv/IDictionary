package com.dimonkiv.idictionary.ui.modules.card

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.*
import com.dimonkiv.idictionary.utills.FragmentById
import com.dimonkiv.idictionary.data.models.FragmentData
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.ui.modules.MainActivity

class CardFragment : Fragment(), ICardContract.Fragment {

    private lateinit var root: View
    private lateinit var presenter: CardPresenter
    private lateinit var view: CardView

    companion object {
        private const val CARD_ID = "cardId"

        fun getBundle(cardId: String): Bundle {
            val bundle = Bundle()
            bundle.putString(CARD_ID, cardId)

            return bundle
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_card, container, false)

        initPresenter()
        initView()
        resumeBundle()

        return root
    }

    private fun initPresenter() {
        presenter = CardPresenter(this)
    }

    private fun initView() {
        view = CardView(this, presenter, getMainActivity(), root, context!!)
    }

    private fun resumeBundle() {
       if (arguments?.containsKey(CARD_ID)!!) {
           arguments?.getString(CARD_ID)?.let { presenter.setCardId(it) }
       }
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