package com.buyin.dalili.features.chat.presentation.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.viewModels
import com.buyin.dalili.databinding.InfoPostRoomBinding
import com.buyin.dalili.features.chat.domain.model.ChatModel
import com.buyin.dalili.features.chat.presentation.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreatePostDialog(val listSize: Int, val roomId: String) : AppCompatDialogFragment() {

    private lateinit var binding: InfoPostRoomBinding
    private val viewModel: ChatViewModel by viewModels()


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        isCancelable = false
        binding = InfoPostRoomBinding.inflate(requireActivity().layoutInflater, null, false)
        val builder = AlertDialog.Builder(activity)
        builder.setView(binding.root)
            .setTitle("write your info ")
        listener()

        return builder.create()

    }


    private fun listener() {
        onClickBtnAdd()
    }


    private fun onClickBtnAdd() {
        val APP_PREF = "appPre"
        val preferences: SharedPreferences =
            requireContext().getSharedPreferences(APP_PREF, Context.MODE_PRIVATE)

        binding.btnAdd.setOnClickListener {
            if (binding.etRoomDescription.text?.isEmpty() == true) {
                binding.etRoomDescription.error = "Please enter name "
            } else {
                viewModel.addChat(
                    ChatModel(
                        roomId = roomId,
                        chatId = listSize,
                        description = binding.etRoomDescription.text.toString(),
                        postLink = binding.etMeetingLink.text.toString(),
                        name = preferences.getString("name", "test")
                    )
                )
                dismiss()
            }
        }
    }

}



