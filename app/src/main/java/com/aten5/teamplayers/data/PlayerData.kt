package com.aten5.teamplayers.data

data class PlayerData(
    val playerID: Int,
    val name: String,
    val age: Int,
    val club: String,
    val isFavorite: Boolean,
) : ComponentData
