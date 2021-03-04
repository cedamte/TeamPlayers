package com.aten5.teamplayers.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aten5.teamplayers.data.PlayerData

@Dao
interface PlayerDao {
    @Query("Select * From fav_players")
    fun getAll(): LiveData<List<PlayerData>>

    @Insert(entity = PlayerData::class, onConflict = OnConflictStrategy.REPLACE)
    fun addPlayer(playerData: PlayerData)

    @Delete(entity = PlayerData::class)
    fun removePlayer(playerData: PlayerData)
}
