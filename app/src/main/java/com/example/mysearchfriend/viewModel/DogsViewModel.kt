package com.example.mysearchfriend.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mysearchfriend.model.dataSource.Dogs
import com.example.mysearchfriend.model.repository.DogRepository
import com.example.mysearchfriend.model.response.ResponseDogs
import com.example.mysearchfriend.model.response.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DogsViewModel : ViewModel() {

    private val dogRepository = DogRepository()
    val liveDogData = MutableLiveData<Dogs>()

    fun getDogs() {
        CoroutineScope(Dispatchers.IO).launch {
            liveDogData.postValue(dogRepository.getDogs())
        }
    }
}