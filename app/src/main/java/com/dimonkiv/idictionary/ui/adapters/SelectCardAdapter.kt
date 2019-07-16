package com.dimonkiv.idictionary.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.data.models.Card


class SelectCardAdapter(private val callback: Callback) : androidx.recyclerview.widget.RecyclerView.Adapter<SelectCardAdapter.ViewHolder>() {

    interface Callback {
        fun onSelectCard(id: String)
    }

    private val cardList = ArrayList<Card>()

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_select_card, parent, false))
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        val card = cardList[pos]
        holder.bind(card)
    }

    fun setItems(cardList: List<Card>) {
        this.cardList.clear()
        this.cardList.addAll(cardList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        private val titleTV: TextView = itemView.findViewById(R.id.title_tv)

        fun bind(card: Card) {
            titleTV.text = card.title

            titleTV.setOnClickListener {
                callback.onSelectCard(card.id)
            }
        }
    }
}