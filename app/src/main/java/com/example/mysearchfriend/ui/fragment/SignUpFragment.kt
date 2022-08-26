package com.example.mysearchfriend.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.mysearchfriend.databinding.FragmentSignUpBinding
import com.example.mysearchfriend.model.response.State
import com.example.mysearchfriend.viewModel.LoginFireStoreViewModel


class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private val userViewModel by activityViewModels<LoginFireStoreViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        actions()
        observers()

        return binding.root
    }

    private fun call(){
        userViewModel.login(binding.etUser.text.toString(),binding.etName.text.toString(),binding.etPassword.text.toString())
    }

    private fun actions(){
        binding.btLoginFireStore.setOnClickListener {
            call()
        }
    }

    private fun observers(){
        userViewModel.getResult().observe(viewLifecycleOwner){
            // validar el campo it.state
            casos(it.state)
        }
    }

    private fun casos(estado: State){
        when(estado){
            State.SUCCESS ->{} // enviar al onboarding
            State.ERROR ->{}// mensaje de no Registrado
        }
    }
}