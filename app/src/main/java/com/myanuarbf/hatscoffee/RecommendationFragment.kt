package com.myanuarbf.hatscoffee

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.myanuarbf.hatscoffee.adapter.MyAdapter
import com.myanuarbf.hatscoffee.adapter.RecommendationAdapter
import com.myanuarbf.hatscoffee.databinding.FragmentRecommendationBinding


class RecommendationFragment : Fragment() {
    private lateinit var binding : FragmentRecommendationBinding
    private lateinit var recRecyclerview: RecyclerView
    private lateinit var dbref: DatabaseReference
    private lateinit var recArrayList: ArrayList<recommendation>
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecommendationBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        val sharedPreferences = requireContext().getSharedPreferences("UserData", Context.MODE_PRIVATE)

        recRecyclerview = binding.rvRecommendation
        recRecyclerview.layoutManager = LinearLayoutManager(context)
        recRecyclerview.setHasFixedSize(true)
        recArrayList = arrayListOf()
        getUserData()
    }
    private fun getUserData() {
        dbref = FirebaseDatabase.getInstance().getReference("rekomendasi")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (menuSnapshot in snapshot.children) {
                        val rekomendasi = menuSnapshot.getValue(recommendation::class.java)
                        rekomendasi?.let {
                            recArrayList.add(it)
                        }
                    }
                    recRecyclerview.adapter = RecommendationAdapter(recArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

}