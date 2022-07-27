package com.example.mysearchfriend.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginFireStoreViewModel : ViewModel() {

    val liveStateLogin = MutableLiveData<Boolean>()

    fun checkState(email: String, password: String) {

        if (email.isNotEmpty() && password.isNotEmpty() &&
            verifyEmail(email) && verifyPassword(password)
        ) {
            liveStateLogin.postValue(true)
        } else {
            liveStateLogin.postValue(false)
        }
    }

    private fun verifyEmail(confirmEmail: String): Boolean {
        return confirmEmail.matches("[a-zA-Z0-9]+".toRegex())
    }

    private fun verifyPassword(password: String): Boolean {
        return password.matches("[a-zA-Z0-9]+".toRegex())
    }
}