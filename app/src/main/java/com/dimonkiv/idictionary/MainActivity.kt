package com.dimonkiv.idictionary

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.dimonkiv.idictionary.dictionary.DictionaryFragment


class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBottomNavigationMenu()
        changeFragment(FragmentData(FragmentById.DICTIONARY_FRAGMENT))
    }

    private fun initBottomNavigationMenu() {
        bottomNavigation = findViewById(R.id.navigation_menu)

        bottomNavigation.setOnNavigationItemSelectedListener {
            if (it.itemId == R.id.navigation_dictionary) {
                changeFragment(FragmentData(FragmentById.DICTIONARY_FRAGMENT))
            } else {

            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    fun changeFragment(fragmentData: FragmentData) {
        val fragmentById = fragmentData.getFragmentById()

        when (fragmentById) {
            FragmentById.DICTIONARY_FRAGMENT -> addFragmentToContainer(DictionaryFragment(), null)
        }
    }

    private fun addFragmentToContainer(fragment: Fragment, bundle: Bundle?) {
        if (bundle != null) {
            fragment.arguments = bundle
        }

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()

    }
}
