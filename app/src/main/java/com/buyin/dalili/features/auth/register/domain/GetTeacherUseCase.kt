package com.buyin.dalili.features.auth.register.domain

import com.buyin.dalili.features.auth.register.data.AccountModel
import com.buyin.dalili.features.auth.register.data.RegisterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTeacherUseCase @Inject constructor(private val repository: RegisterRepository) {

    operator fun invoke(): Flow<List<AccountModel>> {
        return repository.getTeacher()
    }

}
