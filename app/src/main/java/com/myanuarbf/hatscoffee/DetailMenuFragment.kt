package com.myanuarbf.hatscoffee

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.myanuarbf.hatscoffee.databinding.FragmentDetailMenuBinding

class DetailMenuFragment : Fragment() {
    lateinit var binding: FragmentDetailMenuBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var namaMinuman = arguments?.getString("namaMinuman")
        var foto = arguments?.getString("foto")
        var keterangan = arguments?.getString("keterangan")
        var harga = arguments?.getString("harga")

        Log.d("Foto URL", "URL : $foto")

        binding.name.setText(namaMinuman)
        Glide.with(view).load(foto.toString()).into(binding.imgDetail)
        binding.deskripsi.setText(keterangan)
        binding.harga.setText(harga)


        binding.btnInc.setOnClickListener{
            var input = binding.jumlah.text.toString()
            val minus = input.toInt() - 1
            binding.jumlah.setText(minus.toString())
        }
        binding.btndec.setOnClickListener{
            var input = binding.jumlah.text.toString()
            val plus = input.toInt() + 1
            binding.jumlah.setText(plus.toString())
        }
        binding.pesanSekarang.setOnClickListener{
            var input = binding.jumlah.text.toString().toInt()
            var total = input.toInt() * harga!!.toInt()
            val bundle = Bundle()
            bundle.putInt("total", total)
            bundle.putString("namaMinuman", namaMinuman)
            bundle.putString("foto", foto)
            bundle.putString("jumlah", input.toString())
            findNavController().navigate(R.id.action_detailMenuFragment_to_paymentFragment,bundle)
        }
    }


}