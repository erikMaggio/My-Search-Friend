package com.example.mysearchfriend.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mysearchfriend.databinding.FragmentErrorAlertHomeBinding


class ErrorAlertHomeFragment : Fragment() {

    private lateinit var binding: FragmentErrorAlertHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentErrorAlertHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

}