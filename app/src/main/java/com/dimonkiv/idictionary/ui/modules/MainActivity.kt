package com.dimonkiv.idictionary.ui.modules

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.dimonkiv.idictionary.utills.FragmentById.*
import com.dimonkiv.idictionary.data.models.FragmentData
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.ui.modules.create.CreateFragment
import com.dimonkiv.idictionary.ui.modules.create.addword.AddWordFragment
import com.dimonkiv.idictionary.ui.modules.createcard.CreateCardFragment
import com.dimonkiv.idictionary.ui.modules.dictionary.DictionaryFragment
import com.dimonkiv.idictionary.ui.modules.settings.SettingsFragment
import com.dimonkiv.idictionary.ui.modules.wordgame.WordGameFragment

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var progressBar: RelativeLayout
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = findViewById(R.id.progress_bar)
        initBottomNavigationMenu()
        changeFragment(FragmentData(DICTIONARY_FRAGMENT))
    }

    private fun initBottomNavigationMenu() {
        bottomNavigation = findViewById(R.id.navigation_menu)

        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.fragment_game -> changeFragment(FragmentData(WORD_GAME_FRAGMENT))

                R.id.fragment_card -> changeFragment(FragmentData(DICTIONARY_FRAGMENT))

                R.id.fragment_add -> changeFragment(FragmentData(CREATE_FRAGMENT))

                R.id.fragment_statistic -> changeFragment(FragmentData(STATISTIC_FRAGMENT))

                R.id.fragment_settings -> changeFragment(FragmentData(SETTINGS_FRAGMENT))
            }

            return@setOnNavigationItemSelectedListener true
        }
    }

    fun changeFragment(fragmentData: FragmentData) {

        when (fragmentData.getFragmentById()) {
            WORD_GAME_FRAGMENT -> addFragmentToContainer(WordGameFragment(), fragmentData.getBundle())

            DICTIONARY_FRAGMENT -> addFragmentToContainer(DictionaryFragment(), null)

            CREATE_FRAGMENT -> addFragmentToContainer(CreateFragment(), null)

            SETTINGS_FRAGMENT -> addFragmentToContainer(SettingsFragment(), null)

            ADD_WORD_FRAGMENT -> addFragmentToContainer(AddWordFragment(), null)

            CREATE_CARD_FRAGMENT -> addFragmentToContainer(CreateCardFragment(), null)

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

    fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    fun showToast(msg: String) {
        Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
    }
}
