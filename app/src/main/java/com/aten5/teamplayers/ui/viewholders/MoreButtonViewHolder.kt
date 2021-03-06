package com.aten5.teamplayers.ui.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aten5.teamplayers.R
import com.aten5.teamplayers.data.ComponentData
import com.aten5.teamplayers.data.MoreButtonData
import com.aten5.teamplayers.ui.AdapterViewHolder
import kotlinx.android.synthetic.main.more_button_holder.view.*

class MoreButtonViewHolder(
    private val data: MoreButtonData,
) : AdapterViewHolder {

    private lateinit var onMoreClickLister: OnMoreClickLister

    override fun from(viewGroup: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.more_button_holder, viewGroup, false)

        return provideViewHolder(view)
    }

    override fun bind(viewHolder: RecyclerView.ViewHolder) {
        viewHolder.itemView.btn_more.text = data.title
        viewHolder.itemView.btn_more.setOnClickListener {
            onMoreClickLister.onMoreClick(data.title)
        }
    }

    override fun getData(): ComponentData = data

    fun setOnClickListener(lister: OnMoreClickLister) {
        this.onMoreClickLister = lister
    }
}

interface OnMoreClickLister {
    fun onMoreClick(searchType: String)
}
