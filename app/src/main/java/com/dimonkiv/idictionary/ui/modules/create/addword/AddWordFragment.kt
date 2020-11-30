package com.dimonkiv.idictionary.ui.modules.create.addword

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.data.models.FragmentData
import com.dimonkiv.idictionary.ui.modules.MainActivity
import com.dimonkiv.idictionary.utills.FragmentById

class AddWordFragment : Fragment() {
    private lateinit var root: View
    private lateinit var toolbar: Toolbar

    private val mainActivity: MainActivity
        get() = activity as MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_add_word, container, false)
        initUI()
        return root
    }

    private fun initUI() {
        toolbar = root.findViewById<Toolbar>(R.id.toolbar).apply {
            mainActivity.setSupportActionBar(this)
            mainActivity.supportActionBar?.title = "Додати слово"
            setHasOptionsMenu(true)
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                mainActivity.changeFragment(FragmentData(FragmentById.BACK_FRAGMENT))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar_no_search, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.settings) {

        }
        return super.onOptionsItemSelected(item)
    }
}