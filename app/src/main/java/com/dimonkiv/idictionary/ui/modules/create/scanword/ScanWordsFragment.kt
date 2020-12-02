package com.dimonkiv.idictionary.ui.modules.create.scanword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.data.models.FragmentData
import com.dimonkiv.idictionary.ui.modules.MainActivity
import com.dimonkiv.idictionary.utills.FragmentById

class ScanWordsFragment : Fragment() {

    private lateinit var root: View
    private lateinit var toolbar: Toolbar

    private val mainActivity: MainActivity
        get() = activity as MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_scan_words,container, false)

        initUI()

        return root
    }

    private fun initUI() {
        toolbar = root.findViewById<Toolbar>(R.id.toolbar).apply {
            mainActivity.setSupportActionBar(this)
            setHasOptionsMenu(true)
            mainActivity.supportActionBar?.title = "Сканування слів"
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                mainActivity.changeFragment(FragmentData(FragmentById.BACK_FRAGMENT))
            }
        }
    }
}