package com.aten5.teamplayers.repo

import com.aten5.teamplayers.data.response.MTCMobileResponse
import com.aten5.teamplayers.net.MTCMobileServie
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val service: MTCMobileServie) :
    RemoteDataSource {
    override fun getPlayersTeams(
        searchString: String,
        searchType: String?,
        offset: Int?
    ): Observable<MTCMobileResponse> {
        return service.getPlayersTeams(
            searchString = searchString, searchType = searchType, offset = offset
        ).toObservable()
    }
}