package com.example.mysearchfriend.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mysearchfriend.R
import com.example.mysearchfriend.databinding.FragmentAllDogsBinding
import com.example.mysearchfriend.ui.adapter.DogAdapter
import com.example.mysearchfriend.viewModel.DogsViewModel


class AllDogsFragment : Fragment() {

    private val dogViewModels by activityViewModels<DogsViewModel>()
    private lateinit var binding:FragmentAllDogsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllDogsBinding.inflate(inflater,container,false)

        observers()
        calls()
        actions()

        return binding.root
    }

    private fun observers() {
        dogViewModels.dogLiveData.observe(viewLifecycleOwner){
            initRecyclerView(it.image)
        }

    }

    private fun calls() {
        dogViewModels.getDogRandomList()
    }

    private fun actions() {
        binding.btPrevious.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initRecyclerView(dogList: List<String>) {

        val adapter = DogAdapter(dogList)
        binding.recyclerView.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerView.adapter = adapter
    }
}