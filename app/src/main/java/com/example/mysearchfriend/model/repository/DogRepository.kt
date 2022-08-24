package com.example.mysearchfriend.model.repository

import com.example.mysearchfriend.model.dataSource.DogDataSource
import com.example.mysearchfriend.model.response.ResponseDogRandom
import com.example.mysearchfriend.model.service.ApiServiceDog
import retrofit2.Response

class DogRepository {

    private val dataSource = DogDataSource()

     suspend fun getDogsRandom(): Response<ResponseDogRandom> {
        return dataSource.getDogsRandom()
    }
}