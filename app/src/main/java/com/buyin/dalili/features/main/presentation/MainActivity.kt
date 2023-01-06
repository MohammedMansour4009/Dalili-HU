package com.buyin.dalili.features.main.presentation

import android.content.ClipData
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.buyin.dalili.R
import com.buyin.dalili.databinding.ActivityMainBinding
import com.buyin.dalili.databinding.FragmentCollegeBinding
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var  preferences: SharedPreferences
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
        initListener()
        checkSession()
    }

    private fun checkSession() {
        preferences =
            getSharedPreferences("appPre", Context.MODE_PRIVATE)
        if (!preferences.getString("name","").isNullOrBlank()){
            navController.navigate(R.id.item_college)
        }
    }

    private fun initListener() {
        binding.toolbarMain.setNavigationOnClickListener {
            openDrawer()
        }
//            findNavController(R.id.item_modify).navigate(R.id.item_modify)


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



    fun openDrawer() {
        val drawer = binding.drawerLayout
        drawer.openDrawer(GravityCompat.START)
    }

    private fun hideDrawer() {
        val drawer = binding.drawerLayout
        drawer.close()
    }

    private fun setupNavBottom() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.item_register, R.id.item_splash -> {
                    supportActionBar?.hide()
                    binding.bottomNav.isVisible = false
                    binding.appBar.isVisible = false

                }
                R.id.item_login ->{
                    supportActionBar?.hide()
                    binding.bottomNav.isVisible = false
                    binding.appBar.isVisible = false
                    preferences.edit().clear().apply()
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