package com.aten5.teamplayers.data.response


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("message")
    val message: String,
    @SerializedName("minVer")
    val minVer: String,
    @SerializedName("players")
    val players: List<Player>,
    @SerializedName("request_order")
    val requestOrder: Int,
    @SerializedName("searchString")
    val searchString: String,
    @SerializedName("searchType")
    val searchType: String,
    @SerializedName("serverAlert")
    val serverAlert: String,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("teams")
    val teams: List<Team>
)