package com.aten5.teamplayers.repo

import com.aten5.teamplayers.data.response.MTCMobileResponse
import io.reactivex.rxjava3.core.Observable


interface TeamsPlayerRepository {
    fun getTeamsPlayers(
        searchString: String,
    ): Observable<MTCMobileResponse>

    fun getMore(
        searchString: String,
        searchType: String,
        offset: Int
    ): Observable<MTCMobileResponse>

}