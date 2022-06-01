package com.buyin.dalili.features.auth.register.data

import android.util.Log
import com.buyin.dalili.core.common.queryObserveChildEvent
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterDataSource @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
) {

    fun createAccount(accountModel: AccountModel):Boolean{

        firebaseDatabase.getReference("User/student").child(accountModel.university_id.toString())
            .setValue(accountModel)

        return true
    }



    fun getUser(): Flow<List<AccountModel>> {
        val query: Query = firebaseDatabase.reference
            .child("User/student")
        Log.d("TAG000", "init Query ")

        query.get()
        Log.d("TAG000", "get Query ")

        return query.queryObserveChildEvent().map { data ->
            val chatList = ArrayList<AccountModel>()
            Log.d("TAG000", "response College post obse ")
            data?.children?.forEach { child ->
                val coursesModel = child.getValue(AccountModel::class.java)
                chatList.add(coursesModel!!)
            }
            Log.d("TAG000", "response College post obse ")
            chatList
        }.catch {

            Log.d("TAG000 ", "error Query = ${it.message}")
        }
    }

    fun getTeacher(): Flow<List<AccountModel>> {
        val query: Query = firebaseDatabase.reference
            .child("User/teacher")
        Log.d("TAG000", "init Query ")

        query.get()
        Log.d("TAG000", "get Query ")

        return query.queryObserveChildEvent().map { data ->
            val chatList = ArrayList<AccountModel>()
            Log.d("TAG000", "response College post obse ")
            data?.children?.forEach { child ->
                val coursesModel = child.getValue(AccountModel::class.java)
                chatList.add(coursesModel!!)
            }
            Log.d("TAG000", "response College post obse ")
            chatList
        }.catch {

            Log.d("TAG000 ", "error Query = ${it.message}")
        }
    }

}