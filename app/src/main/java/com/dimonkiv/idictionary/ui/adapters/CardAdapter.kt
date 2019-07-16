package com.dimonkiv.idictionary.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.data.models.Word

class CardAdapter : RecyclerView.Adapter<CardAdapter.ViewHolder>() {
    private val wordList = ArrayList<Word>()

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_card, parent, false))
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        val word = wordList[pos]
        holder.bind(word)
    }

    fun setWordList(wordList: List<Word>) {
        this.wordList.clear()
        this.wordList.addAll(wordList)
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val originalTV: TextView = itemView.findViewById(R.id.original_tv)
        private val translatedTV: TextView = itemView.findViewById(R.id.translated_tv)

        fun bind(word: Word) {
            originalTV.text = word.original
            translatedTV.text = word.translated
        }
    }
}