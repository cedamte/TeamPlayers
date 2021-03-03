package com.aten5.teamplayers.data.response


import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("isNation")
    val isNation: String,
    @SerializedName("teamCity")
    val teamCity: String,
    @SerializedName("teamID")
    val teamID: String,
    @SerializedName("teamName")
    val teamName: String,
    @SerializedName("teamNationality")
    val teamNationality: String,
    @SerializedName("teamStadium")
    val teamStadium: String
)