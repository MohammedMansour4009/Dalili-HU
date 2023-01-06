package com.buyin.dalili.features.material.missing.presentation.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.buyin.dalili.databinding.FragmentMissingBinding
import com.buyin.dalili.features.material.missing.data.childrenNumber
import com.buyin.dalili.features.material.missing.domain.model.MissingModel
import com.buyin.dalili.features.material.missing.presentation.MissingViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MissingFragment : Fragment() {

    private lateinit var binding: FragmentMissingBinding
    private val viewModel: MissingViewModel by viewModels()
    private lateinit var imageUri: Uri
    private lateinit var takePictureLauncher: ActivityResultLauncher<Uri>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMissingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        initListener()
    }

    private fun initObserver() {
        viewModel.successGetMissingLiveData.observe(viewLifecycleOwner, ::successGetMissing)
    }

    private fun successGetMissing(list: List<MissingModel>?) {
        if (list == null) return
        val tempList = ArrayList<MissingModel>()
        tempList.addAll(list)
        tempList.reverse()

        val adapter = MissingAdapter(tempList)
        binding.recyclerView.adapter = adapter

    }

    private fun initListener() {
        binding.postId.setOnClickListener {
            Log.d("edit text = ", "initListener: ${binding.editTextId.text.toString()}")
            if (!binding.editTextId.text.isNullOrBlank()&&!binding.phone.text.isNullOrBlank()) {
                if(binding.phone.text.toString().toCharArray().size==10){
                    setMissing(binding.editTextId.text.toString(),binding.phone.text.toString())
                    binding.editTextId.text = null
                }else{
                    binding.textViewErrorPhone.visibility=View.VISIBLE
                }
            }
        }
    }

    private fun setMissing(desc: String,phone: String) {
        val database: DatabaseReference =
            Firebase.database.reference
                .child("lost_item&room/lost_item/Missings/")
        database.child("${childrenNumber+1}").child("description").setValue(desc)
        database.child("${childrenNumber+1}").child("phone").setValue(phone)

    }
}