package com.buyin.dalili.features.bot.presentation.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.buyin.dalili.databinding.BotChatRowBinding
import com.buyin.dalili.features.bot.domain.model.MassegeModel


private const val SENDER = 1
private const val BOT = 2

class MassegeAdapter(val massegeList: ArrayList<MassegeModel>) :
    RecyclerView.Adapter<MassegeAdapter.MassegeHolder>() {

    private lateinit var binding: BotChatRowBinding

    private lateinit var layoutInflater: LayoutInflater
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        layoutInflater = LayoutInflater.from(recyclerView.context)


    }


    class MassegeHolder(private val binding: BotChatRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: MassegeModel) {
            binding.model = model
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setNewMassege(massege: MassegeModel) {
        massegeList.add(massege)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MassegeHolder {
        return MassegeHolder(BotChatRowBinding.inflate(layoutInflater,parent,false))
    }


    override fun onBindViewHolder(holder: MassegeHolder, position: Int) {
        val currentMassege = massegeList[position]
        holder.bind(currentMassege)


    }

    override fun getItemCount(): Int {
        return massegeList.size
    }

}