package com.example.mysearchfriend.model.response

data class ResponseUser(
    val user: User,
    val state: State
)

enum class State {
    SUCCESS, ERROR
}

data class User(
    var email: String?,
    var password: String?,
    var fullName: String?,
)