package com.example.weatherapp.other

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R

class DividerDecoration : RecyclerView.ItemDecoration {
    private var divider: Drawable? = null
    private var dividerWhite: Drawable? = null
    var leftMargin = 0
    var rightMargin = 0
    var startItem = 0
    var endItem = 0
    var isTop = false
    var isBottom = false

    constructor(context: Context?) {
        val contextNotNull = context ?: return
        val styledAttributes = contextNotNull.obtainStyledAttributes(ATTRS)
        divider = styledAttributes.getDrawable(0)
        styledAttributes.recycle()
    }

    constructor(context: Context, resId: Int) {
        divider = ContextCompat.getDrawable(context, resId)
        dividerWhite = ContextCompat.getDrawable(context, R.drawable.item_divider_white)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.getChildLayoutPosition(view) == startItem && !isTop) {
        } else if (parent.getChildLayoutPosition(view) == (parent.adapter?.itemCount
                ?: 0) - 1 - endItem && !isBottom) {
        } else {
            val dividerHeight = divider?.intrinsicHeight ?: 0
            outRect.bottom = if (isTop) 0 else dividerHeight
            outRect.top = if (isTop) dividerHeight else 0
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val divider = divider ?: return

        val left = parent.paddingLeft + leftMargin
        val right = parent.width - parent.paddingRight - rightMargin
        val childCount = parent.childCount
        var child: View
        var params: RecyclerView.LayoutParams?
        var top: Int
        var bottom: Int

        for (i in startItem until childCount) {
            child = parent.getChildAt(i)
            val lp = parent.getChildLayoutPosition(child)
            val childType = parent.getChildViewHolder(child).itemViewType
            params = child.layoutParams as RecyclerView.LayoutParams
            top = child.bottom + params.bottomMargin
            bottom = top + divider.intrinsicHeight
            divider.alpha = (child.alpha * 255f).toInt()

            if (childType == AdapterTypes.PLACE_HOLDER.type) {
                break
            } else if (childType == AdapterTypes.TRANSPARENT.type) {
                break
            } else if (isTop && !isBottom && lp == startItem) {
                divider.setBounds(left, top, right, bottom)
                divider.draw(c)
            } else if (isTop && lp == startItem) {
                divider.setBounds(0, top, right, bottom)
                divider.draw(c)
            } else if (!isTop && lp == startItem) {
            } else if (isBottom && lp >= (parent.adapter?.itemCount ?: 0) - 1 - endItem) {
                divider.setBounds(0, top, right, bottom)
                divider.draw(c)
                break
            } else if (!isBottom && lp >= (parent.adapter?.itemCount ?: 0) - 1 - endItem) {
                break
            } else {
                divider.setBounds(left, top, right, bottom)
                divider.draw(c)
                dividerWhite?.setBounds(0, top, left, bottom)
                dividerWhite?.draw(c)
            }
        }
    }

    companion object {
        private val ATTRS = intArrayOf(android.R.attr.listDivider)
    }
}
