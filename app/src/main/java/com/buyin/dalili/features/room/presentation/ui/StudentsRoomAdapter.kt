package com.buyin.dalili.features.room.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.buyin.dalili.databinding.RowStudentsRoomBinding
import com.buyin.dalili.features.room.domain.model.StudentsRoomModel

class StudentsRoomAdapter(
    private val studentsRooms: List<StudentsRoomModel>, private val onClick: (StudentsRoomModel) -> Unit
) : RecyclerView.Adapter<StudentsRoomAdapter.StudentsRoomViewHolder>() {

    private lateinit var layoutInflater: LayoutInflater

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        layoutInflater = LayoutInflater.from(recyclerView.context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsRoomViewHolder {
        return StudentsRoomViewHolder(RowStudentsRoomBinding.inflate(layoutInflater, parent, false))
    }


    override fun onBindViewHolder(holder: StudentsRoomViewHolder, position: Int) {
        holder.bind(studentsRooms[position])

        holder.itemView.setOnClickListener {
            onClick(studentsRooms[position])
        }
    }

    override fun getItemCount(): Int {
        return studentsRooms.size
    }


    class StudentsRoomViewHolder(private val binding: RowStudentsRoomBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: StudentsRoomModel) {
            binding.model = model
        }
    }
}