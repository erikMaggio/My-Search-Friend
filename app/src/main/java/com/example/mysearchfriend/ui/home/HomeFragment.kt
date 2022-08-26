package com.example.mysearchfriend.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysearchfriend.databinding.FragmentHomeBinding
import com.example.mysearchfriend.model.response.ResponseDogs
import com.example.mysearchfriend.ui.adapter.DogAdapter
import com.example.mysearchfriend.viewModel.DogsViewModel

class HomeFragment : Fragment() {

    private val dogViewModel by activityViewModels<DogsViewModel>()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        actions()
        observers()
        calls()

        return binding.root
    }


    private fun actions() {

    }

    private fun observers() {
        dogViewModel.dogLiveData.observe(viewLifecycleOwner) {
            initRecyclerView(it.image)
        }
    }

    private fun calls() {
        dogViewModel.getDogRandomList()
    }

    private fun initRecyclerView(listDogs: List<String>) {
        val adapter = DogAdapter(listDogs)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.adapter = adapter
    }
}