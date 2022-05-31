package com.buyin.dalili.features.material.college.presentation

import android.util.Log
import androidx.lifecycle.*
import com.buyin.dalili.features.material.college.domain.model.CollegeModel
import com.buyin.dalili.features.material.college.domain.usecase.GetCollegeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollegeViewModel @Inject constructor(
    private val getCollegeUseCase: GetCollegeUseCase
) : ViewModel() {

    private val _successGetCollegeLiveData = MutableLiveData<List<CollegeModel>>()
    var successGetCollegeLiveData: LiveData<List<CollegeModel>> = _successGetCollegeLiveData

    init {
        viewModelScope.launch {
            try {
                Log.d("TAG000", "getCollege")
                successGetCollegeLiveData =
                    getCollegeUseCase().asLiveData(viewModelScope.coroutineContext)
            } catch (e: Exception) {
                Log.d("TAG000", e.message ?: "")
            }
        }
    }
}
