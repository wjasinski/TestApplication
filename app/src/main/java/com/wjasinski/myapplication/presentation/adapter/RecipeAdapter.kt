package com.wjasinski.myapplication.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.wjasinski.myapplication.databinding.ItemRecipeBinding
import com.wjasinski.myapplication.model.Recipe

class RecipeAdapter(val receips: List<Recipe>) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRecipeBinding.inflate(layoutInflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = receips.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(receips.get(position))
    }

    class ViewHolder(val binding : ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(receip: Recipe) {
            binding.receip = receip
            Picasso.get().load(receip.imageUrl).into(binding.ivReceipImage)
        }
    }
}