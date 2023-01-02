package com.buyin.dalili.features.chat.data

import com.buyin.dalili.features.chat.domain.model.ChatModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepository @Inject constructor(
    private val chatDataSource: ChatDataSource,

    ) {
    fun getChat(roomId: String): Flow<List<ChatModel>> {
        return chatDataSource.getChat(roomId = roomId)
    }

    fun addChatModel(model: ChatModel) {
        return chatDataSource.addChatModel(model)
    }
}


