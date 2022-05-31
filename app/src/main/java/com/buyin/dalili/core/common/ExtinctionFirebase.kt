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


suspend fun DatabaseReference.awaitsSingle(): DataSnapshot? =

    suspendCancellableCoroutine { continuation ->
        val listener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                val exception = when (error.toException()) {
                    is FirebaseException -> error.toException()
                    else -> Exception("The Firebase call for reference $this was cancelled")
                }
                continuation.resumeWithException(exception)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    continuation.resume(snapshot)
                } catch (exception: Exception) {
                    continuation.resumeWithException(exception)
                }
            }
        }
        continuation.invokeOnCancellation { this.removeEventListener(listener) }
        this.addListenerForSingleValueEvent(listener)
    }

fun DatabaseReference.observeChildEvent(): Flow<DataSnapshot?> {
    return callbackFlow {
        val listener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                this@callbackFlow.trySend(snapshot).isSuccess
            }
        }
        addValueEventListener(listener)
        awaitClose { removeEventListener(listener) }
    }
}

fun DatabaseReference.singleChildEvent(): Flow<DataSnapshot?> {
    return callbackFlow {
        val listener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                this@callbackFlow.trySend(snapshot).isSuccess
            }
        }
        addListenerForSingleValueEvent(listener)
        awaitClose { removeEventListener(listener) }
    }
}


fun Query.queryObserveChildEvent(): Flow<DataSnapshot?> {
    return callbackFlow {
        val listener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("TAG1212", "response onDataChange ")
                this@callbackFlow.trySend(snapshot).isSuccess
            }
        }
        addValueEventListener(listener)
        awaitClose { removeEventListener(listener) }
    }
}

fun Query.querySingleChildEvent(): Flow<DataSnapshot?> {
    return callbackFlow {
        val listener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                this@callbackFlow.trySend(snapshot).isSuccess
            }
        }
        addListenerForSingleValueEvent(listener)
        awaitClose { removeEventListener(listener) }
    }
}






