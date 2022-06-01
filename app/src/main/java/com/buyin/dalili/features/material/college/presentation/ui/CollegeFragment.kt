package com.buyin.dalili.features.material.college.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.buyin.dalili.R
import com.buyin.dalili.databinding.FragmentCollegeBinding
import com.buyin.dalili.features.material.college.domain.model.CollegeModel
import com.buyin.dalili.features.material.college.presentation.CollegeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CollegeFragment : Fragment() {

    private lateinit var binding: FragmentCollegeBinding

    private val viewModel: CollegeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCollegeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
    }

    private fun initObserver() {
        viewModel.successGetCollegeLiveData.observe(viewLifecycleOwner, ::successGetCollege)
    }

    private fun successGetCollege(list: List<CollegeModel>?) {
        if (list != null) {
            val adapter = CollegeAdapter(list){
                findNavController().navigate(R.id.item_courses)
            }
            binding.recyclerView.adapter = adapter
        }
    }

}