package com.buyin.dalili.features.auth.register.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterRepository @Inject constructor(
    private val dataSource: RegisterDataSource,

    ) {
    fun createAccount(accountModel: AccountModel): Boolean {
        return dataSource.createAccount(accountModel)
    }

    fun getUser(): Flow<List<AccountModel>> {
        return dataSource.getUser()
    }

    fun getTeacher(): Flow<List<AccountModel>> {
        return dataSource.getTeacher()
    }
}


