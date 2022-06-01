package com.buyin.dalili.features.material.college.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.buyin.dalili.databinding.RowCollegeBinding
import com.buyin.dalili.features.material.college.domain.model.CollegeModel

class CollegeAdapter(
    private val colleges: List<CollegeModel>,
    private val onClick:()-> Unit
    ) : RecyclerView.Adapter<CollegeAdapter.CollegeViewHolder>() {

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

        holder.itemView.setOnClickListener {
            onClick()
        }
    }

    override fun getItemCount(): Int {
        return colleges.size
    }


    class CollegeViewHolder(private val binding: RowCollegeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: CollegeModel) {
            binding.model = model
        }
    }
}