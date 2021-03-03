package com.aten5.teamplayers.data.response


import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName("playerAge")
    val playerAge: String,
    @SerializedName("playerClub")
    val playerClub: String,
    @SerializedName("playerFirstName")
    val playerFirstName: String,
    @SerializedName("playerID")
    val playerID: String,
    @SerializedName("playerNationality")
    val playerNationality: String,
    @SerializedName("playerSecondName")
    val playerSecondName: String
)