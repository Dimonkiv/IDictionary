package com.dimonkiv.idictionary.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dimonkiv.idictionary.data.models.Word

@Dao
interface WordDao {

    @Insert
    fun insert(word: Word)

    @Update
    fun update(word: Word)

    @Query("SELECT * FROM word_table WHERE id =:id")
    fun getById(id: String): Word?

    @Query("SELECT * FROM word_table")
    fun getAll(): List<Word>

    @Query("SELECT * FROM word_table WHERE card_id =:cardId")
    fun getAllByCardId(cardId: String): List<Word>

    @Query("DELETE FROM word_table WHERE id=:id")
    fun delete(id: String)
}