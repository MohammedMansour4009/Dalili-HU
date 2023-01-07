package com.buyin.dalili.features.material.courses.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.buyin.dalili.R
import com.buyin.dalili.databinding.FragmentCoursesBinding
import com.buyin.dalili.features.material.courses.domain.model.CoursesModel
import com.buyin.dalili.features.material.courses.presentation.CoursesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoursesFragment : Fragment() {

    private lateinit var binding: FragmentCoursesBinding

    private val viewModel: CoursesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCourses(arguments?.getInt("id").toString())

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoursesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
    }

    private fun initObserver() {
        viewModel.successGetSourcesLiveData.observe(viewLifecycleOwner, ::successGetCourses)
    }

    private fun successGetCourses(list: List<CoursesModel>?) {
        if (list != null) {
            val adapter = CoursesAdapter(list) {
                findNavController().navigate(
                    R.id.item_sources,
                    bundleOf("id" to it.ID)
                )
            }
            binding.recyclerView.adapter = adapter
        }
    }

}