package com.buyin.dalili.features.room.data

import com.buyin.dalili.features.room.domain.model.StudentsRoomModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudentsRoomRepository @Inject constructor(
    private val studentsRoomDataSource: StudentsRoomDataSource,

    ) {
    fun getStudentsRoom(): Flow<List<StudentsRoomModel>> {
        return studentsRoomDataSource.getStudentsRoom()
    }
    fun addStudentsRoomModel(model: StudentsRoomModel){
        return studentsRoomDataSource.addStudentsRoomModel(model)
    }
}


