package com.buyin.dalili.features.auth.createaccount.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.buyin.dalili.R
import com.buyin.dalili.databinding.FragmentCreateAccountBinding

class CreateAccountFragment  : Fragment(){

    private lateinit var binding: FragmentCreateAccountBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
    }

    private fun initListener() {
        binding.buttonLogin.setOnClickListener {
            findNavController().navigate(
                R.id.item_login
            )
        }
    }
}