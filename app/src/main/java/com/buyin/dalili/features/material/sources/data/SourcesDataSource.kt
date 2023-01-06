package com.buyin.dalili.features.material.sources.data

import android.util.Log
import com.buyin.dalili.core.common.queryObserveChildEvent
import com.buyin.dalili.features.chat.domain.model.ChatModel
import com.buyin.dalili.features.material.sources.domain.model.SourcesModel
import com.google.firebase.database.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SourcesDataSource @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val databaseReference: DatabaseReference,
    ) {

    fun getSources(): Flow<List<SourcesModel>> {
        val query: Query = firebaseDatabase.reference
            .child("feature/material/hu/10/courses/100/sources")
        Log.d("TAG000", "init Query ")

        query.get()
        Log.d("TAG000", "get Query ")

        return query.queryObserveChildEvent().map { data ->
            val chatList = ArrayList<SourcesModel>()
            data?.children?.forEach { child ->
                val coursesModel = child.getValue(SourcesModel::class.java)
                chatList.add(coursesModel!!)
            }
            Log.d("TAG000", "response College post obse ")
            chatList
        }.catch {

            Log.d("TAG000 ", "error Query = ${it.message}")
        }
    }

    fun addSources(model: SourcesModel) {
        databaseReference.child("feature/material/hu/10/courses/100/sources").push().setValue(model)
            .addOnSuccessListener {
                Log.d("TAG00", "addItem: ")
            }.addOnFailureListener {
                Log.d("TAG00", "E: =${it.message.toString()}  ")
            }

    }


}