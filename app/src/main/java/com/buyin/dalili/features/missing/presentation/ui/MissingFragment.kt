package com.buyin.dalili.features.missing.presentation.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.buyin.dalili.databinding.FragmentMissingBinding
import com.buyin.dalili.features.missing.data.childrenNumber
import com.buyin.dalili.features.missing.domain.model.MissingModel
import com.buyin.dalili.features.missing.presentation.MissingViewModel
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
            if (!binding.editTextId.text.isNullOrBlank()) {
                if(!binding.phone.text.isNullOrBlank()){
                    if(binding.phone.text.toString().toCharArray().size==10
                        &&binding.phone.text.toString().toCharArray()[0]=='0'&&binding.phone.text.toString().toCharArray()[1]=='7'){
                        setMissing(binding.editTextId.text.toString(),binding.phone.text.toString())
                        binding.editTextId.text = null
                        binding.phone.text=null
                        binding.textViewErrorPhone.text="Pattern Invalid"
                        binding.textViewErrorDesc.visibility=View.INVISIBLE
                        binding.textViewErrorPhone.visibility=View.INVISIBLE
                    }else{
                        binding.textViewErrorPhone.visibility=View.VISIBLE
                    }
                }else{
                    binding.textViewErrorPhone.text="*"
                    binding.textViewErrorPhone.visibility=View.VISIBLE
                }

            }
            else{
                binding.textViewErrorPhone.text="*"
                binding.textViewErrorPhone.visibility=View.VISIBLE
                binding.textViewErrorDesc.visibility=View.VISIBLE
            }
        }
    }

    private fun setMissing(desc: String,phone: String) {
        val database: DatabaseReference =
            Firebase.database.reference
                .child("feature/lost/hu/")
        database.child("${childrenNumber+1}").child("description").setValue(desc)
        database.child("${childrenNumber+1}").child("phone").setValue(phone)

    }
}