package com.dimonkiv.idictionary.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.dimonkiv.idictionary.FragmentById.*
import com.dimonkiv.idictionary.FragmentData
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.ui.card.CardFragment
import com.dimonkiv.idictionary.ui.dictionary.DictionaryFragment


class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBottomNavigationMenu()
        changeFragment(FragmentData(DICTIONARY_FRAGMENT))
    }

    private fun initBottomNavigationMenu() {
        bottomNavigation = findViewById(R.id.navigation_menu)

        bottomNavigation.setOnNavigationItemSelectedListener {
            if (it.itemId == R.id.navigation_dictionary) {
                changeFragment(FragmentData(DICTIONARY_FRAGMENT))
            } else {

            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    fun changeFragment(fragmentData: FragmentData) {
        val fragmentById = fragmentData.getFragmentById()

        when (fragmentById) {
            DICTIONARY_FRAGMENT -> addFragmentToContainer(DictionaryFragment(), null)

            CARD_FRAGMENT -> addFragmentToContainer(CardFragment(), null)

            BACK_FRAGMENT -> onBackPressed()
        }
    }

    private fun addFragmentToContainer(fragment: Fragment, bundle: Bundle?) {
        if (bundle != null) {
            fragment.arguments = bundle
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun addFragmentToDialogContainer(fragment: Fragment, bundle: Bundle?) {
        if (bundle != null) {
            fragment.arguments = bundle
        }

        supportFragmentManager.beginTransaction()
                .replace(R.id.dialog_container, fragment)
                .addToBackStack(null)
                .commit()
    }
}
