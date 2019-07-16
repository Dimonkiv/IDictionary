package com.dimonkiv.idictionary.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.data.models.Card
import com.dimonkiv.idictionary.data.models.Dictionary
import com.dimonkiv.idictionary.ui.widgets.CustomProgressChart

class DictionaryAdapter(private val callback: Callback) : androidx.recyclerview.widget.RecyclerView.Adapter<DictionaryAdapter.ViewHolder>(){

    interface Callback {
        fun onItemClick(cardId: String)

        fun onPlayButtonClick()
    }

    private val cardList = ArrayList<Card>()


    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_dictionary, parent, false))
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        val card = cardList[pos]
        holder.bind(card)
    }

    fun setCardList(cardList: List<Card>) {
        this.cardList.clear()
        this.cardList.addAll(cardList)
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        private val titleTV: TextView = itemView.findViewById(R.id.title_tv)
        private val subtitleTV: TextView = itemView.findViewById(R.id.subtitle_tv)
        private val progressChart: CustomProgressChart = itemView.findViewById(R.id.progress_chart)
        private val containerRL: RelativeLayout = itemView.findViewById(R.id.container_rl)
        private val playIB: ImageButton = itemView.findViewById(R.id.play_ib)

        fun bind(item: Card) {
            titleTV.text = item.title

            containerRL.setOnClickListener {
                callback.onItemClick(item.id)
            }

            playIB.setOnClickListener {
                callback.onPlayButtonClick()
            }
        }
    }
}