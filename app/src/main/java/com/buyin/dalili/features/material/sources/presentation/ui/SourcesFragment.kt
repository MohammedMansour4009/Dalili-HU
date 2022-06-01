package com.buyin.dalili.features.material.sources.presentation.ui

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
import com.buyin.dalili.features.material.sources.domain.model.SourcesModel
import com.buyin.dalili.features.material.sources.presentation.SourcesViewModel
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SourcesFragment : Fragment() {

    private lateinit var binding: FragmentCoursesBinding

    private val viewModel: SourcesViewModel by viewModels()

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
        FirebaseApp.initializeApp(requireContext())
        initObserver()
    }

    private fun initObserver() {
        viewModel.successGetSourcesLiveData.observe(viewLifecycleOwner, ::successGetCourses)
    }

    private fun successGetCourses(list: List<SourcesModel>?) {
        if (list != null) {
            val adapter = SourcesAdapter(list) {
                findNavController().navigate(
                    R.id.item_view_sources,
                    bundleOf(
                        SOURCE_URL to it.url
                    )
                )
            }
            binding.recyclerView.adapter = adapter
        }
    }

    companion object {
        const val SOURCE_URL = "url"
    }
}