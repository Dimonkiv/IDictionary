package com.dimonkiv.idictionary.ui.modules

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import com.dimonkiv.idictionary.utills.FragmentById.*
import com.dimonkiv.idictionary.data.models.FragmentData
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.ui.modules.card.CardFragment
import com.dimonkiv.idictionary.ui.modules.createcard.CreateCardFragment
import com.dimonkiv.idictionary.ui.modules.dictionary.DictionaryFragment
import com.dimonkiv.idictionary.ui.modules.inputtype.InputTypeFragment
import com.dimonkiv.idictionary.ui.modules.newword.NewWordFragment
import com.dimonkiv.idictionary.ui.modules.wordgame.WordGameFragment
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric





class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fabric.with(this, Crashlytics())
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

            INPUT_TYPE_DIALOG_FRAGMENT -> addFragmentToDialogContainer(InputTypeFragment(), null)

            CREATE_CARD_DIALOG_FRAGMENT -> addFragmentToDialogContainer(CreateCardFragment(), null)

            NEW_WORD_DIALOG_FRAGMENT -> addFragmentToDialogContainer(NewWordFragment(), null)

            WORD_GAME_FRAGMENT -> addFragmentToContainer(WordGameFragment(), null)

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
