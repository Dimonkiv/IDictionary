package com.dimonkiv.idictionary.data.preference

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class MainPrefManager private constructor() {
    companion object {
        private const val PREF_NAME = "dictionary_main_preference"
        private const val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"

        private var instance: MainPrefManager? = null
        private var pref: SharedPreferences? = null
        private var editor: SharedPreferences.Editor? = null

        fun getInstance(context: Context): MainPrefManager {
            if (instance == null) {
                instance = MainPrefManager()
                initPreference(context)
            }

            return instance!!
        }

        private fun initPreference(context: Context) {
            pref = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)
            editor = pref?.edit()
        }
    }


    fun setFirstTimeLaunch(isFirstTime: Boolean) {
        editor?.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime)
        editor?.commit()
    }

    fun isFirstTimeLaunch(): Boolean {
        return pref?.getBoolean(IS_FIRST_TIME_LAUNCH, true)!!
    }
}