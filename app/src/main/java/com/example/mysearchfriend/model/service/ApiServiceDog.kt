package com.example.mysearchfriend.model.service

import com.example.mysearchfriend.model.response.ResponseDogs
import retrofit2.Response
import retrofit2.http.GET

interface ApiServiceDog {

    @GET("breeds/image/random/50")
    suspend fun getDogs():Response<ResponseDogs>

}