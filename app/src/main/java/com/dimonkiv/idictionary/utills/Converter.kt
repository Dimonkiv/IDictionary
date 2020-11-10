package com.dimonkiv.idictionary.utills

import androidx.room.TypeConverter
import com.dimonkiv.idictionary.data.models.WordState

class Converter {

    @TypeConverter
    fun fromWordState(wordState: WordState): String {
        return wordState.name
    }

    @TypeConverter
    fun toWordState(wordState: String): WordState {
        return WordState.valueOf(wordState)
    }
}