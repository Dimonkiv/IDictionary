package com.dimonkiv.idictionary.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.models.Dictionary
import com.dimonkiv.idictionary.widgets.CustomProgressChart

class DictionaryAdapter(private val callback: Callback) : RecyclerView.Adapter<DictionaryAdapter.ViewHolder>(){

    interface Callback {
        fun onItemClick()

        fun onPlayButtonClick()
    }

    private val items = ArrayList<Dictionary>()

    init {
        var item = Dictionary(1,"Колода", 100, 65)
        items.add(item)

        item = Dictionary(2,"Колода", 150, 45)
        items.add(item)

        item = Dictionary(3,"Колода", 200, 75)
        items.add(item)

        item = Dictionary(4,"Колода", 250, 25)
        items.add(item)

        item = Dictionary(5,"Колода", 300, 43)
        items.add(item)
    }

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
        private val progressChart: CustomProgressChart = itemView.findViewById(R.id.progress_chart)
        private val containerRL: RelativeLayout = itemView.findViewById(R.id.container_rl)
        private val playIB: ImageButton = itemView.findViewById(R.id.play_ib)

        fun bind(item: Dictionary) {
            titleTV.text = "${item.title} - ${item.id}"
            subtitleTV.text = "${item.countOfWords} слів"
            progressChart.setProgress(item.progress)

            containerRL.setOnClickListener {
                callback.onItemClick()
            }

            playIB.setOnClickListener {
                callback.onPlayButtonClick()
            }
        }
    }
}