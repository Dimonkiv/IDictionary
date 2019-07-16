package com.dimonkiv.idictionary.ui.modules.inputtype

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.*
import android.widget.TextView
import com.dimonkiv.idictionary.utills.FragmentById
import com.dimonkiv.idictionary.data.models.FragmentData
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.ui.modules.MainActivity

class InputTypeFragment : Fragment(), View.OnClickListener {
    private lateinit var root: View

    private lateinit var createCardTV: TextView
    private lateinit var addWordTV: TextView
    private lateinit var backBtn: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_dialog_input_type, container, false)

        initUI()
        return root
    }

    private fun initUI() {
        createCardTV = root.findViewById(R.id.create_new_card_tv)
        addWordTV = root.findViewById(R.id.add_new_word_tv)
        backBtn = root.findViewById(R.id.back_btn)

        createCardTV.setOnClickListener(this)
        addWordTV.setOnClickListener(this)
        backBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v) {

            createCardTV -> showCreateCardDialog()

            addWordTV -> showAddWordsDialog()

            backBtn -> showPreviousFragment()
        }
    }

    private fun showCreateCardDialog() {
        getMainActivity().changeFragment(FragmentData(FragmentById.CREATE_CARD_DIALOG_FRAGMENT))
    }

    private fun showAddWordsDialog() {
        getMainActivity().changeFragment(FragmentData(FragmentById.NEW_WORD_DIALOG_FRAGMENT))
    }

    private fun showPreviousFragment() {
        getMainActivity().changeFragment(FragmentData(FragmentById.BACK_FRAGMENT))
    }

    private fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }
}