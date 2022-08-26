package com.example.mysearchfriend.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.mysearchfriend.databinding.FragmentHomeBinding
import com.example.mysearchfriend.viewModel.DogRandomViewModel

class HomeFragment : Fragment() {

    private val dogViewModel by activityViewModels<DogRandomViewModel>()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        actions()
       // observers()
        calls()

        return binding.root
    }


    private fun actions() {

    }

    private fun observers() {
        dogViewModel.dogLiveData.observe(viewLifecycleOwner){
            //recyclerView con it
        }
    }

    private fun calls() {
        dogViewModel.getDogRandomList()
    }
}