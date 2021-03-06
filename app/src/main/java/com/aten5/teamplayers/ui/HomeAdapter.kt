package com.aten5.teamplayers.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aten5.teamplayers.data.ComponentData
import com.aten5.teamplayers.data.PlayerData

class HomeAdapter<T : AdapterViewHolder> :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data: List<T> = mutableListOf()

    fun updateList(newList: List<T>) {

        val res = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = data.size

            override fun getNewListSize(): Int = newList.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return newList[newItemPosition]::class.java == data[oldItemPosition]::class.java
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val newItem = newList[newItemPosition].getData().id
                val oldItem = data[oldItemPosition].getData().id
                return oldItem == newItem
            }

        })

        data = newList

        res.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        data.find { it.viewType == viewType }!!.from(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]
        item.bind(holder)
    }

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int) = data[position].viewType
}

interface AdapterViewHolder {
    val viewType: Int
        get() = javaClass.hashCode()

    fun provideViewHolder(view: View): RecyclerView.ViewHolder =
        object : RecyclerView.ViewHolder(view) {}

    fun from(viewGroup: ViewGroup): RecyclerView.ViewHolder

    fun bind(viewHolder: RecyclerView.ViewHolder)

    fun getData(): ComponentData
}


