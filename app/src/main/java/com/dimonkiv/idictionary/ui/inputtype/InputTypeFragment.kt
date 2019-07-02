package com.dimonkiv.idictionary.ui.inputtype

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.dimonkiv.idictionary.R

class InputTypeFragment : Fragment() {
    private lateinit var root: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_dialog_input_type, container, false)

        return root
    }
}