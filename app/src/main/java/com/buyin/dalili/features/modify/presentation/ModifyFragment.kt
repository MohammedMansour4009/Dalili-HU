package com.buyin.dalili.features.modify.presentation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.buyin.dalili.R
import com.buyin.dalili.databinding.FragmentModifyBinding
import com.buyin.dalili.features.missing.data.childrenNumber
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class ModifyFragment : Fragment() {
    private lateinit var binding: FragmentModifyBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentModifyBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setData() {
        val preferences: SharedPreferences =
            requireContext().getSharedPreferences("appPre", Context.MODE_PRIVATE)
        binding.editTextAdName.setText(preferences.getString("name", ""))
        binding.editTextUniversity.setText(preferences.getString("universityId", ""))

    }

    private fun init() {
        binding.buttonSave.setOnClickListener {
            val preferences: SharedPreferences =
                requireContext().getSharedPreferences("appPre", Context.MODE_PRIVATE)
            if (isAllValid()) {
                if (preferences.getString("password", "")
                        .toString() == binding.editTextPassword.text.toString()
                ) {

                    saveData(
                        preferences.getString("userType", "")!!,
                        binding.editTextAdName.text.toString(),
                        preferences.getString("universityId", "")!!,
                        binding.editTextUniversity.text.toString(),
                        binding.editTextNewPassword.text.toString()
                    )
                    findNavController().popBackStack(R.id.item_modify, true);
                    findNavController().navigate(R.id.item_college)

                } else {
                    binding.textViewErrorPass.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun saveData(
        userType: String,
        name: String,
        prev_id: String,
        new_id: String,
        new_pass: String
    ) {
        Firebase.database.reference
            .child("User/${userType}/${prev_id}/").removeValue()
        val database: DatabaseReference =
            Firebase.database.reference
                .child("User/${userType}/${new_id}/")
        database.child("name").setValue(name)
        database.child("university_id").setValue(new_id)
        database.child("password").setValue(new_pass)

        val preferences: SharedPreferences =
            requireContext().getSharedPreferences("appPre", Context.MODE_PRIVATE)
        preferences.edit().putString("name", name).apply()
        preferences.edit().putString("universityId", new_id).apply()
        preferences.edit().putString("password", new_pass).apply()
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

        if (binding.editTextPassword.text?.isEmpty() == true || binding.editTextPassword.text!!.length < 4) {
            binding.textViewErrorPass.isVisible = true
            isValid = false
        } else {
            binding.textViewErrorPass.isVisible = false
        }
        if (binding.editTextNewPassword.text?.isEmpty() == true || binding.editTextPassword.text!!.length < 4) {
            binding.textViewErrorNewPass.isVisible = true
            isValid = false
        } else {
            binding.textViewErrorNewPass.isVisible = false
        }
        return isValid
    }

}