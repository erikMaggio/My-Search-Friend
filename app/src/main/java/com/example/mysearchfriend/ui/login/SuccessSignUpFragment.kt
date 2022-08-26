package com.example.mysearchfriend.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mysearchfriend.databinding.FragmentSuccesSignUpBinding


class SuccessSignUpFragment : Fragment() {

    private lateinit var binding:FragmentSuccesSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSuccesSignUpBinding.inflate(inflater,container,false)
        return binding.root
    }
}