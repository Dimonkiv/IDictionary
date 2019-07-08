package com.dimonkiv.idictionary.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dimonkiv.idictionary.R


class SelectCardAdapter(private val callback: Callback) : androidx.recyclerview.widget.RecyclerView.Adapter<SelectCardAdapter.ViewHolder>() {

    interface Callback {
        fun onSelectCard()
    }

    private val items = listOf(1,2,3,4,5)
    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_select_card, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        val item = items[pos]
        holder.bind(item)
    }

    inner class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        private val titleTV: TextView = itemView.findViewById(R.id.title_tv)

        fun bind(item: Int) {
            titleTV.text = "Колода - $item"

            titleTV.setOnClickListener {
                callback.onSelectCard()
            }
        }
    }
}