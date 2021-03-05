package com.aten5.teamplayers.ui.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aten5.teamplayers.R
import com.aten5.teamplayers.data.ComponentData
import com.aten5.teamplayers.data.TeamData
import com.aten5.teamplayers.ui.AdapterViewHolder
import kotlinx.android.synthetic.main.team_holder.view.*

class TeamViewHolder(private val data: TeamData) : AdapterViewHolder {
    override fun from(viewGroup: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.team_holder, viewGroup, false)
        return provideViewHolder(view)
    }

    override fun bind(viewHolder: RecyclerView.ViewHolder) {
        viewHolder.itemView.tv_team_name.text = data.name
        viewHolder.itemView.tv_team_city.text = data.city
        viewHolder.itemView.tv_team_stadium.text = data.stadium
    }

    override fun getData(): ComponentData = data
}