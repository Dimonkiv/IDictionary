package com.dimonkiv.idictionary.ui.widgets

import android.content.Context
import android.widget.TextView
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.data.models.Word
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.annotations.Layout
import com.mindorks.placeholderview.annotations.Resolve
import com.mindorks.placeholderview.annotations.View
import com.mindorks.placeholderview.annotations.swipe.SwipeIn
import com.mindorks.placeholderview.annotations.swipe.SwipeOut

@Layout(R.layout.item_word_game)
class SwipeCardView(private val context: Context,
                    private val swipePlaceHolderView: SwipePlaceHolderView,
                    private val word: Word) {

    @View(R.id.original_tv)
    private lateinit var originalTV: TextView

    @View(R.id.translated_tv)
    private lateinit var translatedTV: TextView

    @Resolve
    private fun onResolve() {
        originalTV.text = word.original
        translatedTV.text = word.translated
    }

    @SwipeIn
    private fun onSwipeIn() {}

    @SwipeOut
    private fun onSwipeOut() {}

}