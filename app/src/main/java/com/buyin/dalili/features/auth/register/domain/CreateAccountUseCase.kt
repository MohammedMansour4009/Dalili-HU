package com.buyin.dalili.features.auth.register.domain

import com.buyin.dalili.features.auth.register.data.AccountModel
import com.buyin.dalili.features.auth.register.data.RegisterRepository
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(private val repository: RegisterRepository) {

    operator fun invoke(accountModel: AccountModel): Boolean {
        return repository.createAccount(accountModel)
    }

}
