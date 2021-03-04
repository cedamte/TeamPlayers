package com.aten5.teamplayers.di

import com.aten5.teamplayers.repo.*
import dagger.Binds
import dagger.Module

@Module
abstract class BuilderModule {
    @Binds
    abstract fun bindTeamsPlayerRepository(teamsPlayerRepositoryImpl: TeamsPlayerRepositoryImpl):
            TeamsPlayerRepository

    @Binds
    abstract fun bindPlayersRepository(playersRepositoryImpl: PlayersRepositoryImpl):
            PlayersRepository

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl):
            RemoteDataSource

    @Binds
    abstract fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl):
            LocalDataSource
}