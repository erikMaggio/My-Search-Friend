package com.example.mysearchfriend.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.example.mysearchfriend.ui.activity.LoginActivity
import com.example.mysearchfriend.databinding.FragmentLoginFireStoreBinding
import com.example.mysearchfriend.viewModel.LoginFireStoreViewModel

class LoginFireStoreFragment : Fragment() {

    private val loginViewModel by viewModels<LoginFireStoreViewModel>()
    private lateinit var binding: FragmentLoginFireStoreBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginFireStoreBinding.inflate(inflater, container, false)

       actions()
        observers()

        return binding.root
    }

    private fun observers(){
        loginViewModel.liveStateLogin.observe(viewLifecycleOwner) {
            binding.btLoginFireStore.isEnabled = it
        }
    }

    private fun actions() {
        binding.etUser.doAfterTextChanged {
            loginViewModel.checkState(
                it.toString(), binding.etPassword.text.toString()
            )
        }
        binding.etPassword.doAfterTextChanged {
            loginViewModel.checkState(
                binding.etUser.text.toString(), it.toString()
            )
        }
        binding.btLoginFireStore.setOnClickListener {

        }
        binding.iconGroup.setOnClickListener {
            startActivity(Intent(context, LoginActivity::class.java))
        }
    }

}