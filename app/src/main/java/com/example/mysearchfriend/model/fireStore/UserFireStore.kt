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
        fireStore.collection("users").get().addOnSuccessListener {
            it.forEach { doc ->
                if (doc.get("email") != email) {
                    fireStore.collection("users").document(email).set(
                        mapOf(
                            "email" to email,
                            "fullName" to fullName,
                            "password" to password
                        )
                    ).addOnSuccessListener {
                        liveUser.postValue(
                            ResponseUser(
                                User(email, password, fullName),
                                State.SUCCESS
                            )
                        )
                    }
                } else {
                    liveUser.postValue(
                        ResponseUser(
                            User(email, password, fullName),
                            State.ERROR
                        )
                    )
                }
            }
        }
    }

    override fun getUser(email: String, password: String) {
        var status = State.LOADING
        fireStore.collection("users").get().addOnSuccessListener {
            it.forEach { doc ->
                if (doc.get("email") == email && doc.get("password") == password) {
                    status = State.SUCCESS
                }
                if (doc.get("email") == email && doc.get("password") != password) {
                    status = State.ERROR_PASSWORD
                }
                if (email == "empty") {
                    status = State.EMPTY
                }
            }
            if (status == State.LOADING) {
                status = State.NO_REGISTER
            }
            when (status) {
                State.SUCCESS -> {
                    liveUser.postValue(
                        ResponseUser(
                            User(email, password, "name"),
                            State.SUCCESS
                        )
                    )
                }
                State.NO_REGISTER -> {
                    liveUser.postValue(
                        ResponseUser(
                            User(email, password, "name"),
                            State.NO_REGISTER
                        )
                    )
                }
                State.EMPTY -> {
                    liveUser.postValue(
                        ResponseUser(
                            User(email, password, "name"),
                            State.EMPTY
                        )
                    )
                }
                State.ERROR_PASSWORD -> {
                    liveUser.postValue(
                        ResponseUser(
                            User(email, password, "name"),
                            State.ERROR_PASSWORD
                        )
                    )
                }
                else -> {
                    liveUser.postValue(
                        ResponseUser(
                            User(email, password, "name"),
                            State.LOADING
                        )
                    )
                }
            }
        }
    }

    fun getStatus(): MutableLiveData<ResponseUser> {
        return liveUser
    }
}