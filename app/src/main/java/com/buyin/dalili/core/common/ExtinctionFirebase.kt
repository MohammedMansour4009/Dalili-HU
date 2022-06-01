package com.buyin.dalili.core.common

import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.database.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


fun Query.queryObserveChildEvent(): Flow<DataSnapshot?> {
    return callbackFlow {
        val listener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
                Log.d("TAG000", "response onCancelled ")

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("TAG000", "response onDataChange ")
                this@callbackFlow.trySend(snapshot).isSuccess
            }
        }
        addValueEventListener(listener)
        awaitClose { removeEventListener(listener) }
    }
}






