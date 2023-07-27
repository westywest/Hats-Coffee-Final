package com.myanuarbf.hatscoffee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.get
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.myanuarbf.hatscoffee.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var databaseRef : DatabaseReference
    var database : FirebaseDatabase?=null

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseRef = database?.reference!!.child("users")

        binding.textViewBacklogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnRegister.setOnClickListener {
            val email = binding.textInputEmailId.text.toString()
            val nama = binding.textInputNamaId.text.toString()
            val telepon = binding.textInputTelepon.text.toString()
            val password = binding.textInputPassId.text.toString()



            if (email.isNotEmpty() && nama.isNotEmpty() && password.isNotEmpty()) {
                if (password.length > 5) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)

                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                val currentUser = firebaseAuth.currentUser
                                val currentUserDb = databaseRef?.child((currentUser?.uid!!))
                                currentUserDb?.child("nama")?.setValue(nama)
                                currentUserDb?.child("telepon")?.setValue(telepon)
                                currentUserDb?.child("email")?.setValue(email)
                                Toast.makeText(this, "Register Berhasil! Silahkan Login!", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, LoginActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                } else {
                    Toast.makeText(this, "Password tidak boleh kurang dari 6!!", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(this, "Data tidak boleh kosong!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

