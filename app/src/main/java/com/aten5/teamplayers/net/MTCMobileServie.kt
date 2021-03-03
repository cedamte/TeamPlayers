package com.aten5.teamplayers.net

import com.aten5.teamplayers.BuildConfig
import com.aten5.teamplayers.data.response.MTCMobileResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.POST
import retrofit2.http.Query


interface MTCMobileServie {
    @POST(BuildConfig.END_POINT)
    fun getPlayersTeams(
        @Query("searchString") searchString: String
    ): Single<MTCMobileResponse>
}