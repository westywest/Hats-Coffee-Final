package com.myanuarbf.hatscoffee

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.myanuarbf.hatscoffee.adapter.MyAdapter
import com.myanuarbf.hatscoffee.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var dbref: DatabaseReference
    private lateinit var menuRecyclerview: RecyclerView
    private lateinit var menuArrayList: ArrayList<menu>
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        val sharedPreferences = requireContext().getSharedPreferences("UserData", Context.MODE_PRIVATE)

        menuRecyclerview = binding.menuList
        menuRecyclerview.layoutManager = LinearLayoutManager(context)
        menuRecyclerview.setHasFixedSize(true)
        menuArrayList = arrayListOf()
        getUserData()
    }

    private fun getUserData() {
        dbref = FirebaseDatabase.getInstance().getReference("menu")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (menuSnapshot in snapshot.children) {
                        val menu = menuSnapshot.getValue(menu::class.java)
                        menu?.let {
                            menuArrayList.add(it)
                        }
                    }
                    menuRecyclerview.adapter = MyAdapter(menuArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }


}
