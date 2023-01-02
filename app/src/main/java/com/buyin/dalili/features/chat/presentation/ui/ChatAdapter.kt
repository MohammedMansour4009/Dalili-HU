package com.buyin.dalili.features.chat.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.buyin.dalili.databinding.RowChatBinding
import com.buyin.dalili.features.chat.domain.model.ChatModel

class ChatAdapter(
    private val chats: List<ChatModel>, private val onClick: () -> Unit
) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    private lateinit var layoutInflater: LayoutInflater

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        layoutInflater = LayoutInflater.from(recyclerView.context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(RowChatBinding.inflate(layoutInflater, parent, false))
    }


    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(chats[position])

        holder.itemView.setOnClickListener {
            onClick()
        }
    }

    override fun getItemCount(): Int {
        return chats.size
    }


    class ChatViewHolder(private val binding: RowChatBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: ChatModel) {
            binding.model = model
        }
    }
}