package com.buyin.dalili.features.chat.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.buyin.dalili.R
import com.buyin.dalili.databinding.FragmentChatBinding
import com.buyin.dalili.features.chat.domain.model.ChatModel
import com.buyin.dalili.features.chat.presentation.ChatViewModel
import com.buyin.dalili.features.room.presentation.ui.CreateRoomDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding

    private val viewModel: ChatViewModel by viewModels()

    var list: List<ChatModel> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getChat(arguments?.getString("roomId")!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        initListener()
    }

    private fun initListener() {

    }

    private fun initObserver() {
        viewModel.successGetChatLiveData.observe(
            viewLifecycleOwner,
            ::successGetChat
        )
    }

    private fun successGetChat(list: List<ChatModel>?) {
        if (list != null) {
            this.list = list
            val adapter = ChatAdapter(list) {
                findNavController().navigate(R.id.item_courses)
            }
            binding.recyclerView.adapter = adapter
        }
    }

}