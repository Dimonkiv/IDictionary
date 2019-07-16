package com.dimonkiv.idictionary.data.models

import android.os.Bundle
import com.dimonkiv.idictionary.utills.FragmentById

class FragmentData(private val fragmentById: FragmentById) {
    private var bundle: Bundle? = null

    constructor(fragmentById: FragmentById, bundle: Bundle) : this(fragmentById) {
        this.bundle = bundle
    }

    fun getBundle(): Bundle? {
        return bundle
    }

    fun getFragmentById(): FragmentById {
        return fragmentById
    }
}