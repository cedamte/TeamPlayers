package com.aten5.teamplayers.ui.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aten5.teamplayers.R
import com.aten5.teamplayers.data.ComponentData
import com.aten5.teamplayers.data.HeaderData
import com.aten5.teamplayers.ui.AdapterViewHolder
import kotlinx.android.synthetic.main.header_holder.view.*

class HeaderViewHolder(private val data: HeaderData) : AdapterViewHolder {
    override fun from(viewGroup: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.header_holder, viewGroup, false)

        return provideViewHolder(view)
    }

    override fun bind(viewHolder: RecyclerView.ViewHolder) {
        viewHolder.itemView.tv_header.text = data.title
    }

    override fun getData(): ComponentData = data

}