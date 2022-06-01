package com.buyin.dalili.features.auth.register.presentation

import android.util.Log
import androidx.lifecycle.*
import com.buyin.dalili.features.auth.register.data.AccountModel
import com.buyin.dalili.features.auth.register.domain.CreateAccountUseCase
import com.buyin.dalili.features.auth.register.domain.GetTeacherUseCase
import com.buyin.dalili.features.auth.register.domain.GetUsersUseCase
import com.buyin.dalili.features.material.courses.domain.model.CoursesModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val createAccountUseCase: CreateAccountUseCase,
    private val getUsersUseCase: GetUsersUseCase,
    private val getTeacherUseCase: GetTeacherUseCase
) : ViewModel() {

    private val _successGetUsersLiveData = MutableLiveData<List<AccountModel>>()
    var successGetUserLiveData: LiveData<List<AccountModel>> = _successGetUsersLiveData


    private val _successGetTeacherLiveData = MutableLiveData<List<AccountModel>>()
    var successGetTeacherLiveData: LiveData<List<AccountModel>> = _successGetUsersLiveData


    fun createAccount(accountModel: AccountModel): Boolean {
        return createAccountUseCase(accountModel)
    }

    init {
        getUsers()
        getTeacher()
    }

    private fun getUsers() {
        viewModelScope.launch {
            try {
                Log.d("TAG000", "getCollege")
                successGetUserLiveData =
                    getUsersUseCase().asLiveData(viewModelScope.coroutineContext)
            } catch (e: Exception) {
                Log.d("TAG000", e.message ?: "")
            }
        }
    }
    private fun getTeacher() {
        viewModelScope.launch {
            try {
                Log.d("TAG000", "getCollege")
                successGetTeacherLiveData =
                    getTeacherUseCase().asLiveData(viewModelScope.coroutineContext)
            } catch (e: Exception) {
                Log.d("TAG000", e.message ?: "")
            }
        }
    }


}
