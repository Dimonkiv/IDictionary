package com.dimonkiv.idictionary.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "card_table")
data class Card(
        @PrimaryKey
        @ColumnInfo(name = "id")
        var id: String = UUID.randomUUID().toString(),
        @ColumnInfo(name = "title")
        var title: String = ""
)
