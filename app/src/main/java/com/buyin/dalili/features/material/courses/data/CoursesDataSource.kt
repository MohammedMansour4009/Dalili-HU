package com.buyin.dalili.features.material.courses.data

import android.util.Log
import com.buyin.dalili.core.common.queryObserveChildEvent
import com.buyin.dalili.features.material.courses.domain.model.CoursesModel
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoursesDataSource @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
) {

    fun getCourses(): Flow<List<CoursesModel>> {
        val query: Query = firebaseDatabase.reference
            .child("feature/material/hu/10/courses")
        Log.d("TAG000", "init Query ")

        query.get()
        Log.d("TAG000", "get Query ")

        return query.queryObserveChildEvent().map { data ->

            val chatList = ArrayList<CoursesModel>()

            data?.children?.forEach { child ->
                val coursesModel = child.getValue(CoursesModel::class.java)
                chatList.add(coursesModel!!)
            }
            Log.d("TAG000", "response College post obse ")
            chatList
        }.catch {

            Log.d("TAG000 ", "error Query = ${it.message}")
        }
    }


}