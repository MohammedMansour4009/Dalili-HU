package com.buyin.dalili.features.chat.domain.usecase

import com.buyin.dalili.features.chat.data.ChatRepository
import com.buyin.dalili.features.chat.domain.model.ChatModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetChatUseCase @Inject constructor(
    private val repository: ChatRepository
) {

    operator fun invoke(roomId:String): Flow<List<ChatModel>> {
        return repository.getChat(roomId)
    }

}
