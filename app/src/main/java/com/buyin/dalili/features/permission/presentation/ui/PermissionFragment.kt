package com.buyin.dalili.features.permission.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.buyin.dalili.R
import com.buyin.dalili.databinding.FragmentPermissionBinding
import com.buyin.dalili.features.permission.presentation.PermissionViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PermissionFragment : Fragment() {

    private lateinit var binding: FragmentPermissionBinding

    private val viewModel: PermissionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getPermission(arguments?.getString("teacherId")!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPermissionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        binding.buttonGoing.setOnClickListener {
            viewModel.getPermission(arguments?.getString("teacherId")!!)
        }
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.successGetPermissionLiveData.collect {
                binding.isRequest = it
                delay(2000)
                if (it)
                    findNavController().navigate(R.id.item_college)
            }
        }

    }


}