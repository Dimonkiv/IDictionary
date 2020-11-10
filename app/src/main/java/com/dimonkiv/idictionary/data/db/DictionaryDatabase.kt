package com.dimonkiv.idictionary.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dimonkiv.idictionary.data.db.dao.CardDao
import com.dimonkiv.idictionary.data.db.dao.WordDao
import com.dimonkiv.idictionary.data.models.Card
import com.dimonkiv.idictionary.data.models.Word
import com.dimonkiv.idictionary.utills.Converter


@Database(entities = [Word::class, Card::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class DictionaryDatabase : RoomDatabase() {

    abstract val wordDao: WordDao
    abstract val cardDao: CardDao

    companion object {
        private const val DB_NAME = "dictionary_db"

        @Volatile
        private var INSTANCE: DictionaryDatabase? = null

        fun getInstance(context: Context): DictionaryDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DictionaryDatabase::class.java,
                        DB_NAME
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}