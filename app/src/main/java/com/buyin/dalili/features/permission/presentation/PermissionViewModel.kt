package com.buyin.dalili.features.permission.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buyin.dalili.features.permission.domain.usecase.GetPermissionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PermissionViewModel @Inject constructor(
    private val getPermissionUseCase: GetPermissionUseCase
) : ViewModel() {

    private val _successGetPermissionLiveData = MutableStateFlow(false)
    var successGetPermissionLiveData = _successGetPermissionLiveData.asStateFlow()

    private val _verifyLoadingState = MutableStateFlow(false)
    val verifyLoadingState: StateFlow<Boolean> = _verifyLoadingState


    fun getPermission(teacherId: String) {
        viewModelScope.launch {
            try {
                Log.d("TAG000", "getPermission")
                _successGetPermissionLiveData.value = getPermissionUseCase.invoke(teacherId)
            } catch (e: Exception) {
                Log.d("TAG000", e.message ?: "")
            }
        }
    }
}
