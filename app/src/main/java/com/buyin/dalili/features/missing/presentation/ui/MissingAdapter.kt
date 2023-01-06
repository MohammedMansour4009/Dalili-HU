package com.buyin.dalili.features.missing.presentation.ui

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.buyin.dalili.databinding.RowMissingBinding
import com.buyin.dalili.features.missing.domain.model.MissingModel


class MissingAdapter(
    private val items: List<MissingModel>
) : RecyclerView.Adapter<MissingAdapter.MissingViewHolder>() {

    private lateinit var layoutInflater: LayoutInflater

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        layoutInflater = LayoutInflater.from(recyclerView.context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MissingViewHolder {
        return MissingViewHolder(RowMissingBinding.inflate(layoutInflater, parent, false))
    }
    
    override fun onBindViewHolder(holder: MissingViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class MissingViewHolder(private val binding: RowMissingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: MissingModel) {
            binding.model = model
            binding.contactBtn.setOnClickListener{
                Log.d("TAG111", "${binding?.model?.phone}")
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "${binding?.model?.phone}"))
                binding.contactBtn.context.startActivity(intent)
            }
        }
    }
}