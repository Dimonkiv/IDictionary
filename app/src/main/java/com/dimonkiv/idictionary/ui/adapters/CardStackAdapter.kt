package com.dimonkiv.idictionary.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.data.models.Word

class CardStackAdapter: RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {

    private val words = ArrayList<Word>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_word_game, parent, false))
    }

    override fun getItemCount(): Int {
        return words.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        words[position].let {
            holder.bind(it)
        }
    }

    fun setWords(words: List<Word>) {
        this.words.clear()
        this.words.addAll(words)
        notifyDataSetChanged()
    }

    fun getWord(position: Int): Word {
        return words[position]
    }

    inner class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

        private val originalTV: TextView = view.findViewById(R.id.original_tv)
        private val translatedTV: TextView = view.findViewById(R.id.translated_tv)
        private val showTranslateRL: RelativeLayout = view.findViewById(R.id.show_translate_rl)

        fun bind(word: Word) {
            originalTV.text = word.original
            translatedTV.text = word.translated

            showTranslateRL.setOnClickListener {
                translatedTV.visibility = View.VISIBLE
                showTranslateRL.visibility = View.GONE
            }
        }
    }
}