package com.buyin.dalili.features.material.missing.data
import android.util.Log
import com.buyin.dalili.core.common.queryObserveChildEvent
import com.buyin.dalili.features.material.missing.domain.model.MissingModel
import com.google.firebase.database.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

var childrenNumber:Int=0
@Singleton
class MissingDataSource @Inject constructor(private val firebaseDatabase: FirebaseDatabase,){
    fun getMissing(): Flow<List<MissingModel>> {
        val query: Query = firebaseDatabase.reference
            .child(
                    "lost_item&room/lost_item/Missings/")
        query.get()

        return query.queryObserveChildEvent().map { data ->

            val chatList = ArrayList<MissingModel>()
            childrenNumber=data!!.children.count().toInt()
            data?.children?.forEach { child ->
                val missingModel = child.getValue(MissingModel::class.java)
                chatList.add(missingModel!!)
                            }
                        chatList
        }.catch {

            Log.d("TAG000 ", "error Query = ${it.message}")
        }
    }
}

