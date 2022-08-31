package com.example.mysearchfriend.model.repository

import com.example.mysearchfriend.model.dataSource.DogDataSource
import com.example.mysearchfriend.model.dataSource.Dogs

class DogRepository {

    private val dataSource = DogDataSource()

     suspend fun getDogs(): Dogs {
        return dataSource.getDogs()
    }
}