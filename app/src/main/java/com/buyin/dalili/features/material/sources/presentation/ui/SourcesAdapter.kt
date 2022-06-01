package com.buyin.dalili.features.material.sources.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.buyin.dalili.databinding.RowCoursesBinding
import com.buyin.dalili.databinding.RowSourcesBinding
import com.buyin.dalili.features.material.sources.domain.model.SourcesModel

class SourcesAdapter(
    private val sources: List<SourcesModel>,
    private val onClick:(SourcesModel)-> Unit
    ) : RecyclerView.Adapter<SourcesAdapter.SourcesViewHolder>() {

    private lateinit var layoutInflater: LayoutInflater

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        layoutInflater = LayoutInflater.from(recyclerView.context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourcesViewHolder {
        return SourcesViewHolder(RowSourcesBinding.inflate(layoutInflater, parent, false))
    }
    
    override fun onBindViewHolder(holder: SourcesViewHolder, position: Int) {
        val model = sources[position]
        holder.bind(model)

        holder.itemView.setOnClickListener {
            onClick(model)
        }
    }

    override fun getItemCount(): Int {
        return sources.size
    }


    class SourcesViewHolder(private val binding: RowSourcesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: SourcesModel) {
            binding.model = model
        }
    }
}