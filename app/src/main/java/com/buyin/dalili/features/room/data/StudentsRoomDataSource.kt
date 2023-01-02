package com.buyin.dalili.features.room.data

import android.util.Log
import com.buyin.dalili.core.common.queryObserveChildEvent
import com.buyin.dalili.features.room.domain.model.StudentsRoomModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudentsRoomDataSource @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val databaseReference: DatabaseReference,

    ) {

    fun getStudentsRoom(): Flow<List<StudentsRoomModel>> {
        val query: Query = firebaseDatabase.reference
            .child("feature/Room/hu/")

        query.get()

        return query.queryObserveChildEvent().map { data ->
            val chatList = ArrayList<StudentsRoomModel>()
            data?.children?.forEach { child ->
                val studentsRoomModel = child.getValue(StudentsRoomModel::class.java)
                chatList.add(studentsRoomModel!!)
            }
            chatList
        }.catch {

            Log.d("TAG000 ", "error Query = ${it.message}")
        }
    }


    fun addStudentsRoomModel(model: StudentsRoomModel) {
        databaseReference.child("feature/Room/hu/").child(model.roomId.toString()).setValue(model)
            .addOnSuccessListener {
                Log.d("TAG00", "addItem: ")
            }.addOnFailureListener {
                Log.d("TAG00", "E: =${it.message.toString()}  ")
            }

    }
}