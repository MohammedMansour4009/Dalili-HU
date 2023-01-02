package com.buyin.dalili.features.room.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.buyin.dalili.R
import com.buyin.dalili.databinding.FragmentStudentsRoomBinding
import com.buyin.dalili.features.room.domain.model.StudentsRoomModel
import com.buyin.dalili.features.room.presentation.StudentsRoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StudentsRoomFragment : Fragment() {

    private lateinit var binding: FragmentStudentsRoomBinding

    private val viewModel: StudentsRoomViewModel by viewModels()

    var list: List<StudentsRoomModel> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStudentsRoomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        initListener()
    }

    private fun initListener() {
        binding.fab.setOnClickListener {
//            findNavController().navigate(R.id.item_create_room_dialog)
            CreateRoomDialog(list).show(requireFragmentManager(), "")
        }
    }

    private fun initObserver() {
        viewModel.successGetStudentsRoomLiveData.observe(
            viewLifecycleOwner,
            ::successGetStudentsRoom
        )
    }

    private fun successGetStudentsRoom(list: List<StudentsRoomModel>?) {
        if (list != null) {
            this.list = list
            val adapter = StudentsRoomAdapter(list) {
                findNavController().navigate(R.id.item_chat, bundleOf("roomId" to it.roomId.toString()))
            }
            binding.recyclerView.adapter = adapter
        }
    }

}