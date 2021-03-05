package com.aten5.teamplayers.ui.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aten5.teamplayers.R
import com.aten5.teamplayers.data.ComponentData
import com.aten5.teamplayers.data.PlayerData
import com.aten5.teamplayers.ui.AdapterViewHolder
import com.aten5.teamplayers.ui.OnAddClickLister
import kotlinx.android.synthetic.main.player_holder.view.*

class PlayerViewHolder(
    private val data: PlayerData,
) : AdapterViewHolder {

    private lateinit var onAddClickLister: OnAddClickLister
    override fun from(viewGroup: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.player_holder, viewGroup, false)
        return provideViewHolder(view)
    }

    override fun bind(viewHolder: RecyclerView.ViewHolder) {
        viewHolder.itemView.tv_player_name.text = data.name
        viewHolder.itemView.tv_player_age.text = data.age.toString()
        viewHolder.itemView.tv_player_club.text = data.club

        viewHolder.itemView.btn_fav.setOnClickListener {
            onAddClickLister.onAddClick(playerData = data)
        }
    }

    override fun getData(): ComponentData = data

    fun setOnClickListener(lister: OnAddClickLister) {
        this.onAddClickLister = lister
    }
}