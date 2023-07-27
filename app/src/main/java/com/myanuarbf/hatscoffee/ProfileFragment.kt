package com.myanuarbf.hatscoffee

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.myanuarbf.hatscoffee.databinding.FragmentHomeBinding
import com.myanuarbf.hatscoffee.databinding.FragmentProfileBinding
class ProfileFragment() : Fragment() {
    lateinit var binding : FragmentProfileBinding
    lateinit var auth : FirebaseAuth
    lateinit var sharedPreferences : SharedPreferences
    private lateinit var firebaseUser : FirebaseUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences("UserData", Context.MODE_PRIVATE)

        binding.btnLogout.setOnClickListener{
            val editor : SharedPreferences.Editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
            }


        auth = FirebaseAuth.getInstance()
        firebaseUser = FirebaseAuth.getInstance().currentUser!!
        loadProfile()
        }


    private fun loadProfile(){
        val userRef = FirebaseDatabase.getInstance().getReference().child("users").child(firebaseUser.uid)
        userRef?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val user = snapshot.getValue<users>(users::class.java)
                    binding.txtInputNama.text = user!!.nama
                    binding.txtInputTlp.text = user!!.telepon
                    binding.txtInputEmail.text = user!!.email
                }

                fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}


