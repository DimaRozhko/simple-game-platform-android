package com.sgp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sgp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val authNavView: BottomNavigationView = binding.authNavView

        val navController = findNavController(R.id.nav_host_fragment_auth_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_login, R.id.navigation_signup, R.id.navigation_user_inf, R.id.navigation_game_inf, R.id.navigation_game_tab
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        authNavView.setupWithNavController(navController)
    }

    fun activateToGameState() {
        binding.authNavView.menu.findItem(R.id.navigation_user_inf).isVisible = true
        binding.authNavView.menu.findItem(R.id.navigation_user_inf).isEnabled = true

        binding.authNavView.menu.findItem(R.id.navigation_game_inf).isVisible = true
        binding.authNavView.menu.findItem(R.id.navigation_game_inf).isEnabled = true

        binding.authNavView.menu.findItem(R.id.navigation_game_tab).isVisible = true
        binding.authNavView.menu.findItem(R.id.navigation_game_tab).isEnabled = true

        binding.authNavView.menu.findItem(R.id.navigation_login).isVisible = false
        binding.authNavView.menu.findItem(R.id.navigation_login).isEnabled = false

        binding.authNavView.menu.findItem(R.id.navigation_signup).isVisible = false
        binding.authNavView.menu.findItem(R.id.navigation_signup).isEnabled = false
    }
}