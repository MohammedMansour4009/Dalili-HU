package com.buyin.dalili.features.material.missing.presentation

import android.util.Log
import androidx.lifecycle.*
import com.buyin.dalili.features.material.missing.domain.model.MissingModel
import com.buyin.dalili.features.material.missing.domain.usecase.GetMissingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MissingViewModel @Inject constructor(
    private val getMissingUseCase: GetMissingUseCase
) : ViewModel() {

    private val _successGetMissingLiveData = MutableLiveData<List<MissingModel>>()
    var successGetMissingLiveData: LiveData<List<MissingModel>> = _successGetMissingLiveData

    init {
        viewModelScope.launch {
            try {
                Log.d("TAG000", "getMissing")
                successGetMissingLiveData =
                    getMissingUseCase().asLiveData(viewModelScope.coroutineContext)
            } catch (e: Exception) {
                Log.d("TAG000", e.message ?: "$e")
            }
        }
    }
}
