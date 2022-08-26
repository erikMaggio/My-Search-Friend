package com.example.mysearchfriend.viewModel

import android.util.Patterns.EMAIL_ADDRESS
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mysearchfriend.model.fireStore.UserFireStore
import com.example.mysearchfriend.model.response.ResponseUser

class LoginFireStoreViewModel : ViewModel() {

    private val prueba = UserFireStore()
    val liveStateLogin = MutableLiveData<Boolean>()

    fun checkState(email: String, password: String) {
        liveStateLogin.postValue(
            email.isNotEmpty() && password.isNotEmpty() &&
                    verifyEmail(email) && verifyPassword(password)
        )
    }

    fun login(email: String, fullName: String, password: String) {
        prueba.addUser(email, fullName, password)
    }

    fun getResult(): MutableLiveData<ResponseUser> {
        var data = MutableLiveData<ResponseUser>()
        prueba.getStatus().observeForever {
            data.postValue(it)
        }
        return data
    }

    private fun verifyEmail(confirmEmail: String): Boolean {
        return EMAIL_ADDRESS.matcher(confirmEmail).matches()//mal la validacion
    }

    private fun verifyPassword(password: String): Boolean {
        return password.matches("[a-zA-Z0-9]+".toRegex())
    }
}


