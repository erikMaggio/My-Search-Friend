package com.example.mysearchfriend.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import com.example.mysearchfriend.databinding.FragmentSignUpBinding
import com.example.mysearchfriend.model.response.State
import com.example.mysearchfriend.ui.onboarding.OnBoardingActivity
import com.example.mysearchfriend.viewModel.UserViewModel


class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private val userViewModel by activityViewModels<UserViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        actions()
        observers()

        return binding.root
    }

    private fun call() {
        userViewModel.login(
            binding.etEmail.text.toString(),
            binding.etName.text.toString(),
            binding.etPassword.text.toString()
        )
    }

    private fun actions() {
        binding.btCreate.setOnClickListener {
            call()
        }
        binding.etEmail.doAfterTextChanged {
            userViewModel.checkStateRegister(
                it.toString(),
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            )
        }
        binding.etName.doAfterTextChanged {
            userViewModel.checkStateRegister(
                binding.etEmail.text.toString(),
                it.toString(),
                binding.etPassword.text.toString()
            )
        }
        binding.etPassword.doAfterTextChanged {
            userViewModel.checkStateRegister(
                binding.etEmail.text.toString(),
                binding.etName.text.toString(),
                it.toString()
            )
        }
    }

    private fun observers() {
        userViewModel.getResult().observe(viewLifecycleOwner) {
            // validar el campo it.state
            case(it.state)
        }

        userViewModel.liveStateRegister.observe(viewLifecycleOwner) {
            binding.btCreate.isEnabled = it
        }
    }

    private fun case(state: State) {
        when (state) {
            State.SUCCESS -> {
                startActivity(Intent(context, OnBoardingActivity::class.java))
            } // enviar al onboarding
            State.ERROR -> {
                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
            }// mensaje de no Registrado
        }
    }
}