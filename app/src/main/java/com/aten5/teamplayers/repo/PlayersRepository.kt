package com.aten5.teamplayers.repo

import androidx.lifecycle.LiveData
import com.aten5.teamplayers.data.PlayerData

interface PlayersRepository {
    fun getAllPlayers(): LiveData<List<PlayerData>>
    fun addPlayer(playerData: PlayerData)
    fun removePlayer(playerData: PlayerData)
}