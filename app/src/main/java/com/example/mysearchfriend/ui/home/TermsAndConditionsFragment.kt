package com.example.mysearchfriend.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mysearchfriend.R
import com.example.mysearchfriend.databinding.FragmentTermsAndConditionsBinding

class TermsAndConditionsFragment : Fragment() {

    private lateinit var binding: FragmentTermsAndConditionsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTermsAndConditionsBinding.inflate(inflater, container, false)
        return binding.root
    }
}