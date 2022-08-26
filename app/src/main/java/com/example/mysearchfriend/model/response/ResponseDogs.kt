package com.example.mysearchfriend.model.response

import com.google.gson.annotations.SerializedName

data class ResponseDogs(
    @SerializedName("message") val image: List<String>,
    @SerializedName("status") val status: String
)


