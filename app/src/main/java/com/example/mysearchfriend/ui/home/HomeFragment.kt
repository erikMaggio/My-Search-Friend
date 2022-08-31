package com.example.mysearchfriend.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysearchfriend.R
import com.example.mysearchfriend.databinding.FragmentHomeBinding
import com.example.mysearchfriend.model.response.ResponseDogs
import com.example.mysearchfriend.model.response.State
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
        binding.tvAllDogs.setOnClickListener {
            findNavController().navigate(R.id.all_dog_fragment)
        }
        binding.tvDogRandom.setOnClickListener {
            findNavController().navigate(R.id.dog_random_fragment)
        }
        binding.tvSearch.setOnClickListener {
            findNavController().navigate(R.id.search_dog_fragment)
        }
        binding.tvUs.setOnClickListener {
            findNavController().navigate(R.id.us_fragment)
        }
    }

    private fun observers() {
        dogViewModel.liveDogData.observe(viewLifecycleOwner) {
            initRecyclerView(it.body.image)
            controlState(it.status)
        }

    }

    private fun calls() {
        controlState(State.LOADING)
        dogViewModel.getDogs()
    }

    private fun initRecyclerView(listDogs: List<String>) {
        val adapter = DogAdapter(listDogs)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.adapter = adapter
    }

    private fun controlState(state: State) {
        when (state) {
            State.SUCCESS -> {
                binding.recyclerView.visibility = View.VISIBLE
                binding.pbLoading.root.visibility = View.GONE
                //layout error in Gone
            }
            State.LOADING -> {binding.recyclerView.visibility = View.GONE
                binding.pbLoading.root.visibility = View.VISIBLE
            }
            State.ERROR -> {}
        }
    }
}