package com.dimonkiv.idictionary

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class DictionaryAdapter : RecyclerView.Adapter<DictionaryAdapter.ViewHolder>(){
    private val items = listOf("Колода 1", "Колода 2", "Колода 3", "Колода 4")

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_dictionary, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        val item = items[pos]
        holder.bind(item)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTV: TextView = itemView.findViewById(R.id.title_tv)
        private val subtitleTV: TextView = itemView.findViewById(R.id.subtitle_tv)

        fun bind(item: String) {
            titleTV.text = item
        }
    }
}