package com.example.mysearchfriend.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mysearchfriend.R
import com.example.mysearchfriend.databinding.ActivityOnBoardingBinding
import com.example.mysearchfriend.ui.fragment.HomeFragment

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actions()
    }

    private fun actions() {
        binding.imageView2.setOnClickListener {
            startActivity(Intent(this,DrawerActivity::class.java))
        }
    }
}