package com.example.mysearchfriend.model.fireStore

import androidx.lifecycle.MutableLiveData
import com.example.mysearchfriend.model.response.ResponseUser
import com.example.mysearchfriend.model.response.State
import com.example.mysearchfriend.model.response.User
import com.google.firebase.firestore.FirebaseFirestore

class UserFireStore : UserFireStoreService {

    private val fireStore = FirebaseFirestore.getInstance()
    private val liveUser = MutableLiveData<ResponseUser>()

    override fun addUser(email: String, fullName: String, password: String) {

        //control de estado aca


        fireStore.collection("users").document(email).set(
            mapOf(
                "email" to email,
                "fullName" to fullName,
                "password" to password
            )
        ).addOnSuccessListener {
            liveUser.postValue(ResponseUser(User(email, password, fullName), State.SUCCESS))
        }
    }

    fun getStatus(): MutableLiveData<ResponseUser> {
        return liveUser
    }
}