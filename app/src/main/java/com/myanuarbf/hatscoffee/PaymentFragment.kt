package com.myanuarbf.hatscoffee

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.myanuarbf.hatscoffee.databinding.FragmentPaymentBinding

class PaymentFragment : Fragment() {

    lateinit var binding : FragmentPaymentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var namaMinuman = arguments?.getString("namaMinuman")
        var total = arguments?.getInt("total")
        var foto = arguments?.getString("foto")
        var jumlah = arguments?.getString("jumlah")


        binding.name.setText(namaMinuman.toString())
        binding.jumlahPemesanan.setText(jumlah.toString())
        binding.totalHarga.setText(total.toString())

        val currentUserId = FirebaseAuth.getInstance().currentUser?.uid

        val transaksi = transaksi(
            namaMinuman = namaMinuman ?: "",
            jumlah = jumlah!!.toString(),
            total = total!!.toString(),
            foto = foto ?: ""
        )

        val databaseReference = FirebaseDatabase.getInstance().reference
            .child("transaksi")
            .child(currentUserId ?: "")

        binding.btnPesan.setOnClickListener{
            val newTransactionRef = databaseReference.push()
            newTransactionRef.setValue(transaksi)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        val bundle = Bundle()
                        bundle.putInt("total", total)
                        findNavController().navigate(R.id.action_paymentFragment_to_receiptFragment, bundle)
                    } else {

                    }
                }
        }
    }
}