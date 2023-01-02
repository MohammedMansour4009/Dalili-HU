package com.buyin.dalili.features.room.presentation

import android.util.Log
import androidx.lifecycle.*
import com.buyin.dalili.features.room.domain.model.StudentsRoomModel
import com.buyin.dalili.features.room.domain.usecase.AddStudentsRoomUseCase
import com.buyin.dalili.features.room.domain.usecase.GetStudentsRoomUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentsRoomViewModel @Inject constructor(
    private val getStudentsRoomUseCase: GetStudentsRoomUseCase,
    private val addStudentsRoomUseCase: AddStudentsRoomUseCase,
) : ViewModel() {

    private val _successGetStudentsRoomLiveData = MutableLiveData<List<StudentsRoomModel>>()
    var successGetStudentsRoomLiveData: LiveData<List<StudentsRoomModel>> =
        _successGetStudentsRoomLiveData

    init {
        viewModelScope.launch {
            try {
                Log.d("TAG000", "getStudentsRoom")
                successGetStudentsRoomLiveData =
                    getStudentsRoomUseCase().asLiveData(viewModelScope.coroutineContext)
            } catch (e: Exception) {
                Log.d("TAG000", e.message ?: "")
            }
        }
    }

    fun addStudentsRoom(model: StudentsRoomModel){
        addStudentsRoomUseCase(model)
    }
}
