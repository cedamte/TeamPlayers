package com.aten5.teamplayers.repo

import androidx.lifecycle.LiveData
import com.aten5.teamplayers.data.PlayerData
import javax.inject.Inject

class PlayersRepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource) :
    PlayersRepository {
    override fun getAllPlayers(): LiveData<List<PlayerData>> {
        return localDataSource.getAllPlayers()
    }

    override fun addPlayer(playerData: PlayerData) {
        localDataSource.addPlayer(playerData)
    }

    override fun removePlayer(playerData: PlayerData) {
        localDataSource.removePlayer(playerData)
    }

}