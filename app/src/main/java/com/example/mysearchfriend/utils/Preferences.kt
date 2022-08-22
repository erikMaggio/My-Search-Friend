package com.example.mysearchfriend.utils

import android.content.Context

class Preferences(val context:Context) {

    private var KEY_USER_EMAIL = ""

    private val storage = context.getSharedPreferences("com.example.mysearchfriend", Context.MODE_PRIVATE)

    fun saveUserEmail(user: String) {
        storage.edit().putString(this.KEY_USER_EMAIL, user).apply()
    }

    fun getUserEmail(): String {
        return storage.getString(this.KEY_USER_EMAIL, "")!!
    }

    fun clear() {
        storage.edit().clear().apply()
    }
}