package com.dimonkiv.idictionary.ui.modules.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.data.models.FragmentData
import com.dimonkiv.idictionary.ui.modules.MainActivity
import com.dimonkiv.idictionary.utills.FragmentById

class CreateFragment : Fragment() {

    private lateinit var root: View

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_create, container, false)

        setListeners()

        return root
    }

    private fun setListeners() {
        root.findViewById<RelativeLayout>(R.id.add_word_rl).apply {
            setOnClickListener {
                (activity as MainActivity).changeFragment(FragmentData(FragmentById.ADD_WORD_FRAGMENT))
            }
        }

        root.findViewById<RelativeLayout>(R.id.scan_word_rl).apply {
            setOnClickListener {

            }
        }

        root.findViewById<RelativeLayout>(R.id.import_word_rl).apply {
            setOnClickListener {

            }
        }

        root.findViewById<RelativeLayout>(R.id.create_card_rl).apply {
            setOnClickListener {
                (activity as MainActivity).changeFragment(FragmentData(FragmentById.CREATE_CARD_FRAGMENT))
            }
        }
    }
}