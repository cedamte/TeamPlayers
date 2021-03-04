package com.aten5.teamplayers.data

data class PlayerData(
    override val id: Int,
    val name: String,
    val age: Int,
    val club: String,
) : ComponentData()
