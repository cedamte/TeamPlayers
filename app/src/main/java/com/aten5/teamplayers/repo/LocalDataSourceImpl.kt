package com.aten5.teamplayers.repo

import androidx.lifecycle.LiveData
import com.aten5.teamplayers.data.PlayerData
import com.aten5.teamplayers.db.AppDatabase
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val appDatabase: AppDatabase,
) : LocalDataSource {
    override fun getAllPlayers(): LiveData<List<PlayerData>> {
        return appDatabase.playersDao().getAll()
    }

    override fun addPlayer(playerData: PlayerData) {
        appDatabase.playersDao().addPlayer(playerData)
    }

    override fun removePlayer(playerData: PlayerData) {
        appDatabase.playersDao().removePlayer(playerData)
    }
}