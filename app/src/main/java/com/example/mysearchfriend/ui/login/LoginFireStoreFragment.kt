package com.example.mysearchfriend.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.example.mysearchfriend.databinding.FragmentLoginFireStoreBinding
import com.example.mysearchfriend.model.fireStore.UserFireStore
import com.example.mysearchfriend.model.response.State
import com.example.mysearchfriend.ui.onboarding.OnBoardingActivity
import com.example.mysearchfriend.viewModel.UserViewModel

class LoginFireStoreFragment : Fragment() {

    val fireStore = UserFireStore()
    private val loginViewModel by viewModels<UserViewModel>()
    private lateinit var binding: FragmentLoginFireStoreBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginFireStoreBinding.inflate(inflater, container, false)

        actions()
        observers()
        calls()

        return binding.root
    }

    fun calls() {
        loginViewModel.userCheck(
            binding.etUser.text.toString(),
            binding.etPassword.text.toString()
        )
    }


    private fun observers() {

        loginViewModel.getResult().observe(viewLifecycleOwner) {
            controlState(it.state)
        }

        loginViewModel.liveStateLogin.observe(viewLifecycleOwner) {
            binding.btLoginFireStore.isEnabled = it
        }
    }

    private fun actions() {
        binding.etUser.doAfterTextChanged {
            loginViewModel.checkStateLogin(
                it.toString(), binding.etPassword.text.toString()
            )
        }
        binding.etPassword.doAfterTextChanged {
            loginViewModel.checkStateLogin(
                binding.etUser.text.toString(), it.toString()
            )
        }
        binding.btLoginFireStore.setOnClickListener {

        }
        binding.iconGroup.setOnClickListener {
            startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    private fun controlState(state: State) {
        when (state) {
            State.SUCCESS -> {
                binding.lyAlertDialog.root.visibility = View.GONE
                binding.pbLoading.root.visibility = View.GONE
                startActivity(Intent(context, OnBoardingActivity::class.java))
            }
            State.LOADING -> {
                binding.pbLoading.root.visibility = View.VISIBLE
                binding.lyAlertDialog.root.visibility = View.GONE
            }
            State.NO_REGISTER -> {
                binding.pbLoading.root.visibility = View.GONE
                binding.lyAlertDialog.root.visibility = View.VISIBLE
                //funcion alert dialog
            }
            State.EMPTY -> {
                binding.pbLoading.root.visibility = View.GONE
                binding.lyAlertDialog.root.visibility = View.GONE
                Toast.makeText(context, "the field is empty", Toast.LENGTH_SHORT).show()
            }
            State.ERROR_PASSWORD -> {
                binding.pbLoading.root.visibility = View.GONE
                binding.lyAlertDialog.root.visibility = View.GONE
                Toast.makeText(context, "your password is incorrect", Toast.LENGTH_SHORT).show()
            }
        }
    }

}