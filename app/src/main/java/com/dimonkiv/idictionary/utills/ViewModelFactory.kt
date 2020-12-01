package com.dimonkiv.idictionary.utills

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dimonkiv.idictionary.data.source.CardDataSource
import com.dimonkiv.idictionary.data.source.WordDataSource
import com.dimonkiv.idictionary.ui.modules.card.CardViewModel
import com.dimonkiv.idictionary.ui.modules.create.addword.AddWordViewModel
import com.dimonkiv.idictionary.ui.modules.createcard.CreateCardViewModel
import com.dimonkiv.idictionary.ui.modules.dictionary.DictionaryViewModel
import com.dimonkiv.idictionary.ui.modules.words.WordsViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory private constructor(
        private val wordDataSource: WordDataSource,
        private val cardDataSource: CardDataSource
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
            with(modelClass) {
                when {
                    isAssignableFrom(CardViewModel::class.java) ->
                        CardViewModel(cardDataSource)
                    isAssignableFrom(CreateCardViewModel::class.java) ->
                        CreateCardViewModel(cardDataSource)
                    isAssignableFrom(DictionaryViewModel::class.java) ->
                        DictionaryViewModel(cardDataSource)
                    isAssignableFrom(AddWordViewModel::class.java) ->
                        AddWordViewModel(cardDataSource, wordDataSource)
                    isAssignableFrom(WordsViewModel::class.java) ->
                        WordsViewModel(wordDataSource)
                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T

    companion object {
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(activity: FragmentActivity): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(
                            Injection.provideWordsDataSource(activity.applicationContext),
                            Injection.provideCardDataSource(activity.applicationContext)
                    )
                }
            }

            return INSTANCE!!
        }
    }
}