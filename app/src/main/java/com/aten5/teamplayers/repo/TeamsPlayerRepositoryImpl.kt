package com.aten5.teamplayers.repo

import com.aten5.teamplayers.data.response.MTCMobileResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class TeamsPlayerRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    TeamsPlayerRepository {
    override fun getTeamsPlayers(searchString: String): Observable<MTCMobileResponse> =
        remoteDataSource.getPlayersTeams(searchString, null, null)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun getMore(
        searchString: String,
        searchType: String,
        offset: Int
    ): Observable<MTCMobileResponse> =
        remoteDataSource.getPlayersTeams(searchString, searchType, offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}