package com.example.mysearchfriend.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mysearchfriend.R
import com.example.mysearchfriend.databinding.FragmentSuccesSignUpBinding
import com.example.mysearchfriend.ui.onboarding.OnBoardingActivity


class SuccessSignUpFragment : Fragment() {

    private lateinit var binding:FragmentSuccesSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSuccesSignUpBinding.inflate(inflater,container,false)

        actions()

        return binding.root
    }


    private fun actions() {
        binding.tvSuccess.setOnClickListener {
            startActivity(Intent(context,OnBoardingActivity::class.java))
        }
    }
}