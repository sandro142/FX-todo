package com.example.fx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class UserActivity : AppCompatActivity() {

    private lateinit var Bottom_Nav: BottomNavigationView
    private lateinit var AppBarConfiguration: AppBarConfiguration
    private lateinit var NavController: NavController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        Bottom_Nav = findViewById(R.id.Bottom_Nav)
        AppBarConfiguration = AppBarConfiguration(setOf(R.id.home_Fragment_id, R.id.settings_Fragment_id))
        NavController = findNavController(R.id.fragmentContainerView)
        setupActionBarWithNavController(NavController, AppBarConfiguration)
        Bottom_Nav.setupWithNavController(NavController)



    }
}