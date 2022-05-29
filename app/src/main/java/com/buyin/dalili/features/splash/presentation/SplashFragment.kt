package com.buyin.dalili.features.splash.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.buyin.dalili.R
import com.buyin.dalili.databinding.FragmentMaterialBinding
import com.buyin.dalili.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            delay(2000)
            binding.progressBar.isVisible = false
            binding.imageViewCheck.isVisible = true
            binding.imageViewCircleCheck.isVisible = true
            delay(1000)
            findNavController().navigate(R.id.item_create_account)
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

}