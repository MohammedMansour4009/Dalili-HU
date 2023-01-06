package com.buyin.dalili.features.chat.data

import android.util.Log
import com.buyin.dalili.core.common.queryObserveChildEvent
import com.buyin.dalili.features.chat.domain.model.ChatModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatDataSource @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val databaseReference: DatabaseReference,

    ) {

    fun getChat(roomId: String): Flow<List<ChatModel>> {
        val query: Query = firebaseDatabase.reference
            .child("feature/Room/hu/${roomId}/chat")

        query.get()

        return query.queryObserveChildEvent().map { data ->
            val chatList = ArrayList<ChatModel>()
            data?.children?.forEach { child ->
                val chatModel = child.getValue(ChatModel::class.java)
                chatList.add(chatModel!!)
            }
            chatList
        }.catch {

            Log.d("TAG000 ", "error Query = ${it.message}")
        }
    }


    fun addChatModel(model: ChatModel) {
        databaseReference.child("feature/Room/hu/${model.roomId}/chat").push().setValue(model)
            .addOnSuccessListener {
                Log.d("TAG00", "addItem: ")
            }.addOnFailureListener {
                Log.d("TAG00", "E: =${it.message.toString()}  ")
            }

    }
}