package com.example.mysearchfriend.model.response

data class ResponseUser(
    val user: User,
    val state: State
)

enum class State {
    SUCCESS, ERROR, LOADING, EMPTY, ERROR_PASSWORD, NO_REGISTER
}

data class User(
    var email: String?,
    var password: String?,
    var fullName: String?,
)