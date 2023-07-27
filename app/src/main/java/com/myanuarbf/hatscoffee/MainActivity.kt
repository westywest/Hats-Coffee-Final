package com.myanuarbf.hatscoffee

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.myanuarbf.hatscoffee.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.frame_layout) as NavHostFragment
        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener{ _, destination, _ ->
            when(destination.id){
                R.id.homeFragment, R.id.chattingFragment, R.id.notificationFragment, R.id.profileFragment ->{
                    showBottomNav()
                }
                else -> hideBottomNav()
            }
        }

        binding.bottomView.setupWithNavController(navController)

    }

    private fun showBottomNav() {
        binding.bottomView.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        binding.bottomView.visibility = View.GONE
    }
}