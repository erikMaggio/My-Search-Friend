package com.example.mysearchfriend.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mysearchfriend.R
import com.example.mysearchfriend.databinding.FragmentAllDogsBinding


class AllDogsFragment : Fragment() {

    private lateinit var binding:FragmentAllDogsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllDogsBinding.inflate(inflater,container,false)
        return binding.root
    }

}