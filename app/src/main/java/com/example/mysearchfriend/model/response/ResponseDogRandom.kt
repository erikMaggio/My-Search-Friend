package com.example.mysearchfriend.model.response

import com.google.gson.annotations.SerializedName

data class ResponseDogRandom(
    @SerializedName("message") val image: String,
    @SerializedName("status") val status: String
)


