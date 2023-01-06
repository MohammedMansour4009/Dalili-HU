package com.buyin.dalili.features.room.presentation.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.viewModels
import com.buyin.dalili.databinding.InfoCreateRoomBinding
import com.buyin.dalili.features.room.domain.model.StudentsRoomModel
import com.buyin.dalili.features.room.presentation.StudentsRoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateRoomDialog(val list: List<StudentsRoomModel>) : AppCompatDialogFragment() {

    private lateinit var binding: InfoCreateRoomBinding
    private val viewModel: StudentsRoomViewModel by viewModels()


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        isCancelable = false
        binding = InfoCreateRoomBinding.inflate(requireActivity().layoutInflater, null, false)
        val builder = AlertDialog.Builder(activity)
        builder.setView(binding.root)
            .setTitle("write your info ")
        listener()

        return builder.create()

    }


    private fun listener() {
//        onClickTvAge()
//
//        datePickerDialogListener()
//
        onClickBtnAdd()
    }


    private fun onClickBtnAdd() {
         val APP_PREF = "appPre"
         val preferences: SharedPreferences = requireContext().getSharedPreferences(APP_PREF, Context.MODE_PRIVATE)

        binding.btnAdd.setOnClickListener {
            if (binding.etRoomName.text?.isEmpty() == true) {
                binding.etRoomName.error = "Please enter name "
            } else {
                viewModel.addStudentsRoom(
                    StudentsRoomModel(
                        list.size,
                        binding.etRoomName.text.toString(),
                        getImages(list.size),
                        "Creator By " + binding.etCreator.text.toString(),
                        preferences.getString("universityId", "test")
                    )
                )
                dismiss()
            }
        }
    }

    private fun getImages(number: Int): String {
        return when (number) {
            1 -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQL8M2O7r8WlHJzPxHkaaDDzbE2nnD9ITHXOw&usqp=CAU"
            2 -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQPxjsJk1Qr5TYGpwZtVVVtW5k8b_1R9se6jg&usqp=CAU"
            3 -> "https://media.istockphoto.com/id/1007454868/vector/teacher-with-students-icon.jpg?s=612x612&w=0&k=20&c=-RVY5PaR5rQ8c0kyeYXZPkCVedPmPzZEP-RhMGQe3d8="
            4 -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS4wIN75npnIJ0Fwkj7ktspH0hpQToFjl8kMw&usqp=CAU"
            5 -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPUC9mF0spLPVz5QKJqAFdZiOvFXdmrh_lSqNy6IosYYmmLsPU0I5UxRnO7wmkO5yfqzc&usqp=CAU"
            else -> {
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS4wIN75npnIJ0Fwkj7ktspH0hpQToFjl8kMw&usqp=CAU"
            }
        }
    }
}



