package com.dimonkiv.idictionary.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "word_table")
data class Word(
        @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String = UUID.randomUUID().toString(),
        @ColumnInfo(name = "original")
    var original: String = "",
        @ColumnInfo(name = "translate")
    var translate: String = "",
        @ColumnInfo(name = "card_id")
    var cardId: String = "",
        @ColumnInfo(name = "word_state")
    var state: WordState = WordState.NEW)