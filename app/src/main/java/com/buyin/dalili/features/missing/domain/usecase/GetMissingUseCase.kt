package com.buyin.dalili.features.missing.domain.usecase

import com.buyin.dalili.features.missing.data.MissingRepository
import com.buyin.dalili.features.missing.domain.model.MissingModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetMissingUseCase @Inject constructor(
    private val repository: MissingRepository
) {

    suspend operator fun invoke(): Flow<List<MissingModel>> {
        return repository.getMissing()
    }

}
