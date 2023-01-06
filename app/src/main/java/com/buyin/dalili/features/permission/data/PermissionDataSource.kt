package com.buyin.dalili.features.permission.data

import com.buyin.dalili.core.common.awaitsSingle
import com.buyin.dalili.features.auth.register.data.AccountModel
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PermissionDataSource @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
) {


    suspend fun getPermission(teacherId: String): Boolean {

        val user =
            firebaseDatabase.reference.child("User/teacher/$teacherId/").awaitsSingle()
                ?.getValue(AccountModel::class.java)

        return user?.permission == true
    }
}