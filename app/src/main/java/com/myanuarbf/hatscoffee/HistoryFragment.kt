package com.myanuarbf.hatscoffee

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.R
import com.google.firebase.database.ValueEventListener
import com.myanuarbf.hatscoffee.adapter.HistoryAdapter
import com.myanuarbf.hatscoffee.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {
    lateinit var binding: FragmentHistoryBinding
    private lateinit var databaseRef: DatabaseReference
    private lateinit var adapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HistoryAdapter(ArrayList())
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvHistory.layoutManager = layoutManager
        binding.rvHistory.adapter = adapter

        val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid

        if (currentUserUid != null) {
            databaseRef = FirebaseDatabase.getInstance().getReference("transaksi").child(currentUserUid)
            databaseRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val transaksiList = ArrayList<transaksi>()
                    for (snapshot in dataSnapshot.children) {
                        val transaksi = snapshot.getValue(transaksi::class.java)
                        transaksi?.let { transaksiList.add(it) }
                    }
                    adapter.setData(transaksiList)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle database error
                }
            })
        }
    }
}