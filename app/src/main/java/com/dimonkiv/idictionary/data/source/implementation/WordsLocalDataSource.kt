package com.dimonkiv.idictionary.data.source.implementation

import com.dimonkiv.idictionary.data.db.dao.WordDao
import com.dimonkiv.idictionary.data.models.Word
import com.dimonkiv.idictionary.data.source.WordDataSource
import com.dimonkiv.idictionary.utills.AppExecutors

class WordsLocalDataSource private constructor(
        private val appExecutors: AppExecutors,
        private val wordDao: WordDao
) : WordDataSource {

    override fun getWords(callback: WordDataSource.LoadWordsDataSource) {
        appExecutors.discIO.execute {
            val words = wordDao.getAll()
            appExecutors.mainThread.execute {
                if (words.isEmpty())
                    callback.onDataNotAvailable()
                else
                    callback.onLoadWords(words)
            }
        }
    }

    override fun getWordsByCardId(cardId: String, callback: WordDataSource.LoadWordsDataSource) {
        appExecutors.discIO.execute {
            val words = wordDao.getAllByCardId(cardId)
            appExecutors.mainThread.execute {
                if (words.isEmpty())
                    callback.onDataNotAvailable()
                else
                    callback.onLoadWords(words)
            }
        }
    }

    override fun getWord(id: String, callback: WordDataSource.LoadWordDataSource) {
        appExecutors.discIO.execute {
            val word = wordDao.getById(id)
            appExecutors.mainThread.execute {
                if (word == null)
                    callback.onDataNotAvailable()
                else
                    callback.onLoadWord(word)
            }
        }
    }

    override fun insertWord(word: Word) {
        appExecutors.discIO.execute {
            wordDao.insert(word)
        }
    }

    override fun updateWord(word: Word) {
        appExecutors.discIO.execute {
            wordDao.update(word)
        }
    }

    override fun deleteWord(id: String) {
        appExecutors.discIO.execute {
            wordDao.delete(id)
        }
    }

    companion object {
        private var INSTANCE: WordsLocalDataSource? = null

        @JvmStatic
        fun getInstance(appExecutors: AppExecutors, wordDao: WordDao): WordsLocalDataSource {
            if (INSTANCE == null) {
                synchronized(WordsLocalDataSource::class.java) {
                    INSTANCE = WordsLocalDataSource(appExecutors, wordDao)
                }
            }

            return INSTANCE!!
        }
    }
}