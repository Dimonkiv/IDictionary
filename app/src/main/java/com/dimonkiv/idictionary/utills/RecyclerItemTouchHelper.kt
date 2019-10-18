package com.dimonkiv.idictionary.utills

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.ui.adapters.DictionaryAdapter

class RecyclerItemTouchHelper (private val adapter: DictionaryAdapter,
                               private val recycler: RecyclerView,
                               private val context: Context) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
    private var icon: Drawable? = null
    private var background: ColorDrawable? = null

    init {
        icon = ContextCompat.getDrawable(context, R.drawable.ic_remove)
        background = ColorDrawable(ContextCompat.getColor(context, R.color.colorBackgroundRemove))
    }


    override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(holder: RecyclerView.ViewHolder, p1: Int) {
        val position = holder.adapterPosition
        //adapter.deleteItem(position, recycler)
    }

    override fun onChildDraw(
        c: Canvas, recycler: RecyclerView, holder: RecyclerView.ViewHolder,
        dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        super.onChildDraw(c, recycler, holder, dX, dY, actionState, isCurrentlyActive)

        val itemView = holder.itemView
        val iconMargin = (itemView.height - icon?.intrinsicHeight!!) / 2
        val iconTop = itemView.top + (itemView.height - icon?.intrinsicHeight!!) / 2
        val iconBottom = iconTop + icon?.intrinsicHeight!!

        if(dX < 0) {
            val iconLeft = itemView.right - iconMargin - icon?.intrinsicWidth!!
            val iconRight = itemView.right - iconMargin
            icon?.setBounds(iconLeft, iconTop, iconRight, iconBottom)

            background?.setBounds((itemView.right + dX).toInt(),
                itemView.top, itemView.right, itemView.bottom)
        } else {
            background?.setBounds(0,0,0,0)
        }

        background?.draw(c)
        icon?.draw(c)
    }
}