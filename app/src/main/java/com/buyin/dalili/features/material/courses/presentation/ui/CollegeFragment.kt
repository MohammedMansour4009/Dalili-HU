package com.buyin.dalili.features.material.courses.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.buyin.dalili.databinding.FragmentMaterialBinding
import com.buyin.dalili.features.material.college.domain.model.CollegeModel
import com.buyin.dalili.features.material.college.presentation.CollegeViewModel
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CollegeFragment : Fragment() {

    private lateinit var binding: FragmentMaterialBinding

    private val viewModel: CollegeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMaterialBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FirebaseApp.initializeApp(requireContext())
        initObserver()
    }

    private fun initObserver() {
        viewModel.successGetCollegeLiveData.observe(viewLifecycleOwner, ::successGetCollege)
    }

    private fun successGetCollege(list: List<CollegeModel>?) {
        if (list != null) {
            val adapter = CoursesAdapter(list){
//                findNavController().navigate(R.id.)
            }
            binding.recyclerView.adapter = adapter
        }
    }

}