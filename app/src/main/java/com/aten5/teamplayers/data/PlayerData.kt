package com.aten5.teamplayers.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "fav_players"
)
data class PlayerData(
    @PrimaryKey override val id: Int,
    val name: String,
    val age: Int,
    val club: String,
) : ComponentData()
