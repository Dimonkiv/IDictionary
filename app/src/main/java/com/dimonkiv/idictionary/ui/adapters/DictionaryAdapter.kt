package com.dimonkiv.idictionary.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.data.models.Card
import com.dimonkiv.idictionary.ui.widgets.CustomProgressChart
import com.google.android.material.snackbar.Snackbar

class DictionaryAdapter(private val callback: Callback) : RecyclerView.Adapter<DictionaryAdapter.ViewHolder>(){

    interface Callback {
        fun onItemClick(cardId: String)

        fun onPlayButtonClick(cardId: String)

        fun onRemoveItem(card: Card)

        fun undoRemove()
    }

    private var mRecentlyRemovedCard: Card? = null
    private var mRecentlyRemovedCardPosition: Int? = null
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

    fun removeItem(position: Int, recyclerView: RecyclerView) {
        cardList[position].let {
            mRecentlyRemovedCard = it
            callback.onRemoveItem(it)
            cardList.remove(it)
        }

        mRecentlyRemovedCardPosition = position
        notifyItemRemoved(position)
        showUndoSnackbar(recyclerView)
    }

    private fun showUndoSnackbar(recyclerView: RecyclerView) {
        Snackbar.make(recyclerView, "Карту видалено", Snackbar.LENGTH_LONG).apply {
            setAction("Відмінити") {undoDelete()}
            show()
        }
    }

    private fun undoDelete() {
        cardList.add(mRecentlyRemovedCardPosition!!, mRecentlyRemovedCard!!)
        notifyItemInserted(mRecentlyRemovedCardPosition!!)
        callback.undoRemove()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
                callback.onPlayButtonClick(item.id)
            }
        }
    }
}