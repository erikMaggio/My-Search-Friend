package com.example.mysearchfriend.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mysearchfriend.databinding.FragmentErrorSignUpBinding


class ErrorSignUpFragment : Fragment() {

    private lateinit var binding: FragmentErrorSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentErrorSignUpBinding.inflate(inflater,container,false)
        return binding.root
    }

}