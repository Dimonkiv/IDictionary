package com.dimonkiv.idictionary.utills

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

fun <T: ViewModel> Fragment.obtainViewModel(viewModelClass: Class<T>) =
        ViewModelProviders.of(this, ViewModelFactory.getInstance(activity!!)).get(viewModelClass)