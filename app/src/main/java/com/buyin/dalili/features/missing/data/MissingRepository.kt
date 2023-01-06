package com.buyin.dalili.features.missing.data

import com.buyin.dalili.features.missing.domain.model.MissingModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MissingRepository @Inject constructor(
    private val missingDataSource: MissingDataSource,

    ) {
    fun getMissing(): Flow<List<MissingModel>> {
        return missingDataSource.getMissing()
    }
}


