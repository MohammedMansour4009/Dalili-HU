package com.buyin.dalili.features.room.domain.usecase

import com.buyin.dalili.features.room.data.StudentsRoomRepository
import com.buyin.dalili.features.room.domain.model.StudentsRoomModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetStudentsRoomUseCase @Inject constructor(
    private val repository: StudentsRoomRepository
) {

    operator fun invoke(): Flow<List<StudentsRoomModel>> {
        return repository.getStudentsRoom()
    }

}
