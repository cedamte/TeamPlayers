package com.aten5.teamplayers.data

import com.aten5.teamplayers.ui.AdapterViewHolder
import com.aten5.teamplayers.ui.viewholders.HeaderViewHolder
import com.aten5.teamplayers.ui.viewholders.MoreButtonViewHolder
import com.aten5.teamplayers.ui.viewholders.PlayerViewHolder
import com.aten5.teamplayers.ui.viewholders.TeamViewHolder

fun ComponentData.getViewHolder(): AdapterViewHolder {
    return when (this) {
        is HeaderData -> HeaderViewHolder(this)
        is PlayerData -> PlayerViewHolder(this)
        is TeamData -> TeamViewHolder(this)
        is MoreButtonData -> MoreButtonViewHolder(this)
        else -> throw RuntimeException("🔥 ${this::class.java.canonicalName} has not been added to getBuilderWithData extension function")
    }
}