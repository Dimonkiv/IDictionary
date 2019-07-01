package com.dimonkiv.idictionary

import android.os.Bundle

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