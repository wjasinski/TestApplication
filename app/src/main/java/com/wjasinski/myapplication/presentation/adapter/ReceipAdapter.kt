package com.wjasinski.myapplication.presentation.adapter

import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.wjasinski.myapplication.databinding.ItemReceipBinding
import com.wjasinski.myapplication.model.Receip

class ReceipAdapter(val receips: ObservableList<Receip>) : RecyclerView.Adapter<ReceipAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemReceipBinding.inflate(layoutInflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = receips.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(receips.get(position))
    }

    class ViewHolder(val binding : ItemReceipBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(receip: Receip) {
            binding.receip = receip
            Picasso.get().load(receip.imageUrl).into(binding.ivReceipImage)
        }
    }
}