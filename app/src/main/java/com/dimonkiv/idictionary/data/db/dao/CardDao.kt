package com.dimonkiv.idictionary.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dimonkiv.idictionary.data.models.Card

@Dao
interface CardDao {

    @Insert
    fun insert(card: Card)

    @Update
    fun update(card: Card)

    @Query("SELECT * FROM card_table WHERE id =:id")
    fun getById(id: String): Card?

    @Query("SELECT * FROM card_table")
    fun getAll(): List<Card>

    @Query("DELETE FROM card_table WHERE id=:id")
    fun remove(id: String)

}