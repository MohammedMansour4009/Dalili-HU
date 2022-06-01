package com.buyin.dalili.features.material.courses.presentation

import android.util.Log
import androidx.lifecycle.*
import com.buyin.dalili.features.material.courses.domain.model.CoursesModel
import com.buyin.dalili.features.material.courses.domain.usecase.GetCoursesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoursesViewModel @Inject constructor(
    private val getCoursesUseCase: GetCoursesUseCase
) : ViewModel() {

    private val _successGetCoursesLiveData = MutableLiveData<List<CoursesModel>>()
    var successGetSourcesLiveData: LiveData<List<CoursesModel>> = _successGetCoursesLiveData

    init {
            viewModelScope.launch {
                try {
                    Log.d("TAG000", "getCollege")
                    successGetSourcesLiveData =
                        getCoursesUseCase().asLiveData(viewModelScope.coroutineContext)
                } catch (e: Exception) {
                    Log.d("TAG000", e.message ?: "")
                }
            }
    }
}
