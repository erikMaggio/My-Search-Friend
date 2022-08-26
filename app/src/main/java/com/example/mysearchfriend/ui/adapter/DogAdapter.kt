package com.example.mysearchfriend.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mysearchfriend.R
import com.example.mysearchfriend.databinding.ItemDogsBinding
import com.example.mysearchfriend.model.response.ResponseDogs
import com.squareup.picasso.Picasso

class DogAdapter(private val listDog: List<String>) : RecyclerView.Adapter<DogsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dogs, parent, false)
        return DogsHolder(view)
    }

    override fun onBindViewHolder(holder: DogsHolder, position: Int) {
        holder.render(listDog[position])
    }

    override fun getItemCount(): Int {
        return listDog.size
    }
}

class DogsHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemDogsBinding.bind(view)

    fun render(image:String) {
        Picasso.get().load(image).into(binding.ivImageDog)
    }
}