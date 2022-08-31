package com.example.mysearchfriend.model.dataSource

import com.example.mysearchfriend.model.response.ResponseDogs
import com.example.mysearchfriend.model.response.State
import com.example.mysearchfriend.model.service.ApiServiceDog
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DogDataSource {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dog.ceo/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val implementService = retrofit.create(ApiServiceDog::class.java)


    suspend fun getDogs(): Dogs {
        val call = implementService.getDogs()
        return if(call.isSuccessful){
            Dogs(implementService.getDogs().body()!!,State.SUCCESS)
        } else {
            Dogs(implementService.getDogs().body()!!,State.ERROR)
        }

    }
}

data class Dogs(val body: ResponseDogs, val status: State)