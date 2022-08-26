package com.example.mysearchfriend.model.repository

import com.example.mysearchfriend.model.dataSource.DogDataSource
import com.example.mysearchfriend.model.response.ResponseDogs
import retrofit2.Response

class DogRepository {

    private val dataSource = DogDataSource()

     suspend fun getDogsRandom(): Response<ResponseDogs> {
        return dataSource.getDogsRandom()
    }
}