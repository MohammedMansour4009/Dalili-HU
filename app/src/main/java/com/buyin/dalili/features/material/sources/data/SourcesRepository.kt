package com.buyin.dalili.features.material.sources.data

import com.buyin.dalili.features.material.sources.domain.model.SourcesModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SourcesRepository @Inject constructor(
    private val dataSource: SourcesDataSource,

    ) {
   fun getSources(): Flow<List<SourcesModel>> {
        return dataSource.getSources()
    }
}


