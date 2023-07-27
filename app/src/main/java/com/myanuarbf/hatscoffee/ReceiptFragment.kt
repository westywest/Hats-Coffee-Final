package com.myanuarbf.hatscoffee

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.myanuarbf.hatscoffee.databinding.FragmentReceiptBinding

class ReceiptFragment : Fragment() {
    lateinit var binding : FragmentReceiptBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentReceiptBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var total = arguments?.getInt("total")
        Log.d("Harga", "URL : $total")

        binding.totalHarga.setText(total.toString())
        binding.btnBeranda.setOnClickListener{
            findNavController().navigate(R.id.action_receiptFragment_to_homeFragment)
        }
    }
}