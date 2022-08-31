package com.example.mysearchfriend.model.fireStore


interface UserFireStoreService {

    fun addUser(email: String, fullName: String, password: String)
    fun getUser(email:String,password: String)
}