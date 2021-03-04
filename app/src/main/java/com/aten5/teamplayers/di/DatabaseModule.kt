package com.aten5.teamplayers.di

import android.content.Context
import androidx.room.Room
import com.aten5.teamplayers.BuildConfig
import com.aten5.teamplayers.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, BuildConfig.DATABASE_NAME)
            .build()
    }
}