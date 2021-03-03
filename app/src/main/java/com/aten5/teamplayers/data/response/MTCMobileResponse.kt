package com.aten5.teamplayers.data.response


import com.google.gson.annotations.SerializedName

data class MTCMobileResponse(
    @SerializedName("result")
    val result: Result
)