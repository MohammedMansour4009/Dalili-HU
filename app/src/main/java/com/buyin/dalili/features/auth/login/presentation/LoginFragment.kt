package com.buyin.dalili.features.auth.login.presentation

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.buyin.dalili.R
import com.buyin.dalili.databinding.FragmentLoginBinding

class LoginFragment  : Fragment(){

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
        initView()
    }

    private fun initView() {
        val content = SpannableString(getString(R.string.create_account))
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        binding.textViewLabelCreateAccount.text = content
    }

    private fun initListener() {
        binding.textViewLabelCreateAccount.setOnClickListener {
            findNavController().navigate(
                R.id.ir
            )
        }
    }
}