package com.dimonkiv.idictionary.models

data class Dictionary(
        var id: Int,
        var title: String,
        var countOfWords: Int,
        var progress: Int)