package com.buyin.dalili.features.main.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.buyin.dalili.R
import com.buyin.dalili.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

    }


    private fun init() {
        initNavigation()
        setToolbar()
        setupNavBottom()
        setNavBottomAndDrawerNav()
    }

    private fun setNavBottomAndDrawerNav() {
        setupWithNavController(binding.bottomNav, navController)
        setupWithNavController(binding.navView, navController)
        setupActionBarWithNavController(this, navController, appBarConfiguration)
    }

    private fun initNavigation() {
        navHostFragment =
            (supportFragmentManager.findFragmentById(R.id.fragment_navigation_container)
                    as NavHostFragment)

        navController = navHostFragment.findNavController()

        appBarConfiguration =
            AppBarConfiguration.Builder(navController.graph).setDrawerLayout(binding.drawerLayout)
                .build()


    }

    private fun setToolbar() {
        setupActionBarWithNavController(this, navController, appBarConfiguration)
    }


    private fun setupNavBottom() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.item_login, R.id.item_register, R.id.item_splash -> {
                    supportActionBar?.hide()
                    binding.bottomNav.isVisible = false
                    binding.appBar.isVisible = false

                }
                R.id.item_college -> {
                    supportActionBar?.hide()
                    actionBar?.setDisplayHomeAsUpEnabled(false)
                    binding.appBar.isVisible = true
                }
                else -> {
                    binding.bottomNav.isVisible = true
                    binding.appBar.isVisible = true
                    supportActionBar?.hide()

                }
            }
        }

    }
}