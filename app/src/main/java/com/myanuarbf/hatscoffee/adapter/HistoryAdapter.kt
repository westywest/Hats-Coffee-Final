package com.myanuarbf.hatscoffee.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myanuarbf.hatscoffee.databinding.HistoryItemBinding
import com.myanuarbf.hatscoffee.transaksi

class HistoryAdapter(private val historyList: ArrayList<transaksi>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    class ViewHolder(val binding: HistoryItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtNamaMinuman.text = historyList[position].namaMinuman
        holder.binding.total.text = historyList[position].total.toString()
        holder.binding.jumlah.text = historyList[position].jumlah.toString()
        Glide.with(holder.itemView).load(historyList[position].foto).into(holder.binding.images)

    }

    fun setData(newData: ArrayList<transaksi>) {
        historyList.clear()
        historyList.addAll(newData)
        notifyDataSetChanged()
    }
}