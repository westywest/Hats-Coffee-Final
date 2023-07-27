package com.myanuarbf.hatscoffee.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myanuarbf.hatscoffee.R
import com.myanuarbf.hatscoffee.databinding.RecommendationItemBinding
import com.myanuarbf.hatscoffee.recommendation

class RecommendationAdapter (private val recList : ArrayList<recommendation>) : RecyclerView.Adapter<RecommendationAdapter.MyViewHolder>(){
    class MyViewHolder(val binding : RecommendationItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecommendationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return recList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = recList[position]

        Glide.with(holder.itemView).load(currentItem.foto).into(holder.binding.images)
        holder.binding.txtKet.text = currentItem.keterangan
        holder.binding.txtNamaMinuman.text = currentItem.namaMinuman
    }

}