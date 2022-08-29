package com.example.mysearchfriend.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mysearchfriend.R
import com.example.mysearchfriend.databinding.FragmentSuccessFulAdoptionBinding


class SuccessFulAdoptionFragment : Fragment() {

    private lateinit var binding: FragmentSuccessFulAdoptionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         binding = FragmentSuccessFulAdoptionBinding.inflate(inflater,container,false)
         return binding.root
    }

}