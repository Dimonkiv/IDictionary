package com.dimonkiv.idictionary.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dimonkiv.idictionary.R

class CardAdapter : androidx.recyclerview.widget.RecyclerView.Adapter<CardAdapter.ViewHolder>() {
    private val items = listOf(1,2,3,4,5,6,7)

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_card, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {

    }


    inner class ViewHolder(private val itemView: View): androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

    }
}