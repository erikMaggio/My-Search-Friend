package com.example.mysearchfriend.model.fireStore

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mysearchfriend.model.response.ResponseUser
import com.google.firebase.firestore.FirebaseFirestore

class UserFireStore :UserFireStoreService{
    private val fireStore = FirebaseFirestore.getInstance()
    private val liveUser = MutableLiveData<ResponseUser>()


     override fun addUser(email: String, fullName: String, password: String) {
        fireStore.collection("users").document(email).set(
            mapOf(
                email to "email",
                fullName to "fullName",
                password to "password"
            )
        )
    }

   override fun getUser(email: String): MutableLiveData<ResponseUser> {
        fireStore.collection("users").document(email).get().addOnSuccessListener {
            if (!it.data.isNullOrEmpty()) {
                liveUser.postValue(
                    ResponseUser(
                        it.get(email).toString(),
                        it.get("password").toString(),
                        it.get("fullName").toString()
                    )
                )
            } else {
                liveUser.postValue(
                    ResponseUser(
                        it.get("").toString(),
                        it.get("").toString(),
                        it.get("").toString()
                    )
                )
            }
        }.addOnFailureListener{
            Log.d("app","errors")
        }
        return liveUser
    }
}