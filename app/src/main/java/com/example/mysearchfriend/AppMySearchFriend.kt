package com.example.mysearchfriend

import android.app.Application
import com.example.mysearchfriend.utils.Preferences

class AppMySearchFriend: Application() {
    companion object {
        lateinit var preferences: Preferences
    }

    override fun onCreate() {
        super.onCreate()
        preferences = Preferences(applicationContext)
    }
}