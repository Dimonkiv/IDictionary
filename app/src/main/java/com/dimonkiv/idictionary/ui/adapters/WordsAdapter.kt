package com.dimonkiv.idictionary.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.data.models.Word

class WordsAdapter : RecyclerView.Adapter<WordsAdapter.ViewHolder>() {

    private val wordList = ArrayList<Word>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_words, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val word = wordList[position]
        holder.bind(word)
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    fun setWordList(words: List<Word>) {
        this.wordList.clear()
        this.wordList.addAll(words)
        this.notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val statusTV: TextView = itemView.findViewById(R.id.status_tv)
        private val originalTV: TextView = itemView.findViewById(R.id.original_tv)
        private val translatedTV: TextView = itemView.findViewById(R.id.translated_tv)
        private val microphoneIV: ImageView = itemView.findViewById(R.id.microphone_iv)

        fun bind(word: Word) {
            originalTV.text = word.original
            translatedTV.text = word.translate
            statusTV.text = word.state.name
        }
    }
}