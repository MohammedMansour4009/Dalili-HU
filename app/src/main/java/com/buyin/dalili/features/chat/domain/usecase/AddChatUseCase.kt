package com.buyin.dalili.features.chat.domain.usecase

import com.buyin.dalili.features.chat.data.ChatRepository
import com.buyin.dalili.features.chat.domain.model.ChatModel
import javax.inject.Inject


class AddChatUseCase @Inject constructor(
    private val repository: ChatRepository
) {

    operator fun invoke(model: ChatModel) {
        return repository.addChatModel(model)
    }

}
