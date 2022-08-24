package com.example.mysearchfriend.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mysearchfriend.model.repository.DogRepository
import com.example.mysearchfriend.model.response.ResponseDogRandom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DogRandomViewModel : ViewModel() {

    private val dogRepository = DogRepository()
    val dogLiveData = MutableLiveData<ResponseDogRandom>()

    fun getDogRandomList() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = dogRepository.getDogsRandom()
            if (call.isSuccessful)
                dogLiveData.postValue(call.body())
        }
    }
}