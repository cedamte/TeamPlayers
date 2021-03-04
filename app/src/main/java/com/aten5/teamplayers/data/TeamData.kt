package com.aten5.teamplayers.data

data class TeamData(
    override val id: Int,
    val name: String,
    val city: String,
    val stadium: String,
) : ComponentData()
