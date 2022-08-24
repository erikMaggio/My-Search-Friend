package com.example.mysearchfriend.model.dataSource

import com.example.mysearchfriend.model.response.ResponseDogRandom
import com.example.mysearchfriend.model.service.ApiServiceDog
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DogDataSource {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dog.ceo/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

        suspend fun getDogsRandom(): Response<ResponseDogRandom> {
        return retrofit.create(ApiServiceDog::class.java).getDogsRandom()
    }
}