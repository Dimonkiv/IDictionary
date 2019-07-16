package com.dimonkiv.idictionary.data.models

data class Dictionary(
        var id: Int,
        var title: String,
        var countOfWords: Int,
        var progress: Int)