package com.myanuarbf.hatscoffee.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myanuarbf.hatscoffee.R
import com.myanuarbf.hatscoffee.databinding.MenuItemBinding
import com.myanuarbf.hatscoffee.menu

class MyAdapter(private val menuList: ArrayList<menu>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: MenuItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = menuList[position]

        Glide.with(holder.itemView).load(currentItem.foto).into(holder.binding.images)
        holder.binding.txtharga.text = currentItem.harga.toString()
        holder.binding.txtKet.text = currentItem.keterangan
        holder.binding.txtNamaMinuman.text = currentItem.namaMinuman
        holder.binding.btnpesan.setOnClickListener{
            var bundle = Bundle()
            bundle.putString("namaMinuman", currentItem.namaMinuman)
            bundle.putString("keterangan", currentItem.keterangan)
            bundle.putString("harga", currentItem.harga.toString())
            bundle.putString("foto", currentItem.foto)
            Navigation.findNavController(holder.itemView).navigate(R.id.action_homeFragment_to_detailMenuFragment,bundle)
        }
    }
}
