package com.aten5.teamplayers.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aten5.teamplayers.data.PlayerData

@Database(entities = [PlayerData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playersDao(): PlayerDao
}