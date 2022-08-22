package com.example.mysearchfriend.model.fireStore

import androidx.lifecycle.MutableLiveData
import com.example.mysearchfriend.model.response.ResponseUser

interface UserFireStoreService {

    fun addUser(email: String, fullName: String, password: String)


    fun getUser(email: String): MutableLiveData<ResponseUser>
}