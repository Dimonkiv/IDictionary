package com.dimonkiv.idictionary.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.data.models.Word

class SwipeStackAdapter(private val words: List<Word>): BaseAdapter() {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_word_game, parent, false)
        val originalTV = view.findViewById<TextView>(R.id.original_tv)
        originalTV.text = words[position].original

        return view
    }

    override fun getItem(position: Int): Word {
        return words[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return words.size
    }
}