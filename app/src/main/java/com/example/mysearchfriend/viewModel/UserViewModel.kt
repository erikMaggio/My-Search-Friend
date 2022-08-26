package com.example.mysearchfriend.viewModel

import android.util.Patterns.EMAIL_ADDRESS
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mysearchfriend.model.fireStore.UserFireStore
import com.example.mysearchfriend.model.response.ResponseUser

class UserViewModel : ViewModel() {

    private val prueba = UserFireStore()
    val liveStateLogin = MutableLiveData<Boolean>()
    val liveStateRegister = MutableLiveData<Boolean>()

    fun checkStateLogin(email: String, password: String) {
        liveStateLogin.postValue(
            email.isNotEmpty() &&
                    password.isNotEmpty() &&
                    verifyEmail(email) &&
                    verifyPassword(password)
        )
    }

    fun checkStateRegister(fullName: String, email: String, password: String) {
        liveStateRegister.postValue(
            fullName.isNotEmpty() &&
                    email.isNotEmpty() &&
                    password.isNotEmpty() &&
                    verifyEmail(email) &&
                    verifyPassword(password)
        )
    }

    private fun verifyEmail(confirmEmail: String): Boolean {
        return EMAIL_ADDRESS.matcher(confirmEmail).matches()
    }



    private fun verifyPassword(password: String): Boolean {
        return password.matches("[a-zA-Z0-9]+".toRegex())
    }

    fun login(email: String, fullName: String, password: String) {
        prueba.addUser(email, fullName, password)
    }

    fun getResult(): MutableLiveData<ResponseUser> {
        val data = MutableLiveData<ResponseUser>()
        prueba.getStatus().observeForever {
            data.postValue(it)
        }
        return data
    }
}


