package com.aten5.teamplayers.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HomeAdapter<T : AdapterViewHolder> :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: MutableList<T> = mutableListOf()

    fun updateList(newList: List<T>) {
        data.clear()
        data.addAll(newList)
        notifyDataSetChanged()
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
}