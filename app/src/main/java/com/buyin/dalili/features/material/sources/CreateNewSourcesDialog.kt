package com.buyin.dalili.features.material.sources

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.viewModels
import com.buyin.dalili.databinding.InfoCreateRoomBinding
import com.buyin.dalili.databinding.InfoCreateSourcesBinding
import com.buyin.dalili.features.material.sources.domain.model.SourcesModel
import com.buyin.dalili.features.material.sources.presentation.SourcesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateNewSourcesDialog(val listSize: Int) : AppCompatDialogFragment() {

    private lateinit var binding: InfoCreateSourcesBinding

    private val viewModel: SourcesViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        isCancelable = false
        binding = InfoCreateSourcesBinding.inflate(requireActivity().layoutInflater, null, false)
        val builder = AlertDialog.Builder(activity)
        builder.setView(binding.root)
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
            if (binding.etName.text?.isEmpty() == true) {
                binding.etName.error = "Please enter name "
            } else {
                viewModel.addSources(
                    SourcesModel(
                        listSize,
                        name = binding.etName.text.toString(),
                        url = binding.etUrl.text.toString()
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



