package com.buyin.dalili.features.material.courses.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.buyin.dalili.databinding.RowCollegeBinding
import com.buyin.dalili.features.material.college.domain.model.CoursesModel
import com.buyin.dalili.features.material.courses.domain.model.CoursesModel

class CoursesAdapter(
    private val colleges: List<CoursesModel>,
    private val onClick:()-> Unit
    ) : RecyclerView.Adapter<CoursesAdapter.CollegeViewHolder>() {

    private lateinit var layoutInflater: LayoutInflater

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        layoutInflater = LayoutInflater.from(recyclerView.context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollegeViewHolder {
        return CollegeViewHolder(RowCollegeBinding.inflate(layoutInflater, parent, false))
    }
    
    override fun onBindViewHolder(holder: CollegeViewHolder, position: Int) {
        holder.bind(colleges[position])
    }

    override fun getItemCount(): Int {
        return colleges.size
    }


    class CollegeViewHolder(private val binding: RowCollegeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: CoursesModel) {
            binding.model = model
        }
    }
}