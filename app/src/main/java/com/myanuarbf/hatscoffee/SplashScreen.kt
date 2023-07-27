package com.myanuarbf.hatscoffee

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.myanuarbf.hatscoffee.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    lateinit var binding: ActivitySplashScreenBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)

        Handler().postDelayed({
            checkUserLoginStatus()
            finish() }, 2000)
    }

    private fun checkUserLoginStatus() {
        val email = sharedPreferences.getString("email", null)
        if (email != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }else{
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}