package com.buyin.dalili.features.material.sources.presentation

import android.util.Log
import androidx.lifecycle.*
import com.buyin.dalili.features.material.sources.domain.model.SourcesModel
import com.buyin.dalili.features.material.sources.domain.usecase.AddSourcesUseCase
import com.buyin.dalili.features.material.sources.domain.usecase.GetSourcesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SourcesViewModel @Inject constructor(
    private val getCoursesUseCase: GetSourcesUseCase,
    private val addSourcesUseCase: AddSourcesUseCase,
) : ViewModel() {

    private val _successGetSourcesLiveData = MutableLiveData<List<SourcesModel>>()
    var successGetSourcesLiveData: LiveData<List<SourcesModel>> = _successGetSourcesLiveData

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

    fun addSources(model: SourcesModel){
        addSourcesUseCase(model)
    }
}
