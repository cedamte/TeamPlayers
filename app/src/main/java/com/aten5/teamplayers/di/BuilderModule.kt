package com.aten5.teamplayers.di

import com.aten5.teamplayers.repo.RemoteDataSource
import com.aten5.teamplayers.repo.RemoteDataSourceImpl
import com.aten5.teamplayers.repo.TeamsPlayerRepository
import com.aten5.teamplayers.repo.TeamsPlayerRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class BuilderModule {
    @Binds
    abstract fun bindTeamsPlayerRepository(teamsPlayerRepositoryImpl: TeamsPlayerRepositoryImpl):
            TeamsPlayerRepository

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl):
            RemoteDataSource
}