package com.buyin.dalili.features.auth.register.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.buyin.dalili.R
import com.buyin.dalili.databinding.FragmentRegisterBinding
import com.buyin.dalili.features.auth.register.data.AccountModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    private val viewModel: RegisterViewModel by viewModels()


    private var users = ArrayList<AccountModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        initObserve()
    }

    private fun initObserve() {
        viewModel.successGetUserLiveData.observe(viewLifecycleOwner, ::onSuccessGetUser)
    }

    private fun onSuccessGetUser(list: List<AccountModel>?) {
        if (list != null) {
            users.addAll(list)
        }
    }




    private fun initListener() {
        onCilkButtonRegister()

        binding.buttonRegister.setOnClickListener {
            if (isAllValid()) {
                val account = AccountModel(
                    name = binding.editTextAdName.text.toString(),
                    password = binding.editTextPassword.text.toString(),
                    university_id = binding.editTextUniversity.text.toString(),
                )
                var isUserExist = false
                var isTeacherExist = false

                users.forEach {
                    if (it.university_id == account.university_id) {
                        Toast.makeText(requireContext(), "isExist", Toast.LENGTH_LONG).show()
                        isUserExist = true
                    }
                }



                if (!isUserExist)
                    viewModel.createAccount(account)


            }
        }
    }

    private fun isAllValid(): Boolean {
        var isValid = true

        if (binding.editTextAdName.text?.isEmpty() == true) {
            binding.textViewErrorName.isVisible = true
            isValid = false
        } else {
            binding.textViewErrorName.isVisible = false
        }

        if (binding.editTextUniversity.text?.isEmpty() == true && binding.editTextUniversity.text!!.length != 8) {
            binding.textViewErrorUniversity.isVisible = true
            isValid = false
        } else {
            binding.textViewErrorUniversity.isVisible = false
        }

        if (binding.editTextPassword.text?.isEmpty() == true && binding.editTextUniversity.text!!.length < 4) {
            binding.textViewErrorPass.isVisible = true
            isValid = false
        } else {
            binding.textViewErrorPass.isVisible = false
        }

        return isValid
    }

    private fun onCilkButtonRegister() {
        binding.buttonRegister.setOnClickListener {
            findNavController().navigate(
                R.id.item_college
            )
        }
    }
}