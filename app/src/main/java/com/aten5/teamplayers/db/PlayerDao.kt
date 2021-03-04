package com.aten5.teamplayers.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.aten5.teamplayers.data.PlayerData

@Dao
interface PlayerDao {
    @Query("Select * From fav_players")
    fun getAll(): LiveData<List<PlayerData>>

    @Insert(entity = PlayerData::class)
    fun addPlayer(playerData: PlayerData)

    @Delete(entity = PlayerData::class)
    fun removePlayer(playerData: PlayerData)
}
