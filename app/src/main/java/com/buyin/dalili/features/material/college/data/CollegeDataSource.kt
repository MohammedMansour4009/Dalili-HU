package com.buyin.dalili.features.material.college.data

import android.util.Log
import com.buyin.dalili.core.common.queryObserveChildEvent
import com.buyin.dalili.features.material.college.domain.model.CollegeModel
import com.google.firebase.database.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CollegeDataSource @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
) {

    fun getMaterial(): Flow<List<CollegeModel>> {
        val query: Query = firebaseDatabase.reference
            .child("feature/material/hu/")
        Log.d("TAG000", "init Query ")

        query.get()
        Log.d("TAG000", "get Query ")

        return query.queryObserveChildEvent().map { data ->
            val chatList = ArrayList<CollegeModel>()
            data?.children?.forEach { child ->
                val collegeModel = child.getValue(CollegeModel::class.java)
                chatList.add(collegeModel!!)
            }
            Log.d("TAG000", "response College post obse ")
            chatList
        }.catch() {

            Log.d("TAG000 ", "error Query = ${it.message}")
        }
    }


}