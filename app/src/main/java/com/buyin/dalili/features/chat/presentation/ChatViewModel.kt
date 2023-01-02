package com.buyin.dalili.features.chat.presentation

import android.util.Log
import androidx.lifecycle.*
import com.buyin.dalili.features.chat.domain.model.ChatModel
import com.buyin.dalili.features.chat.domain.usecase.AddChatUseCase
import com.buyin.dalili.features.chat.domain.usecase.GetChatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getChatUseCase: GetChatUseCase,
    private val addChatUseCase: AddChatUseCase,
) : ViewModel() {

    private val _successGetChatLiveData = MutableLiveData<List<ChatModel>>()
    var successGetChatLiveData: LiveData<List<ChatModel>> =
        _successGetChatLiveData



    init {

    }

    fun getChat(roomId:String){
        viewModelScope.launch {
            try {
                Log.d("TAG000", "getChat")
                successGetChatLiveData =
                    getChatUseCase(roomId).asLiveData(viewModelScope.coroutineContext)
            } catch (e: Exception) {
                Log.d("TAG000", e.message ?: "")
            }
        }
    }

    fun addChat(model: ChatModel) {
        addChatUseCase(model)
    }
}
