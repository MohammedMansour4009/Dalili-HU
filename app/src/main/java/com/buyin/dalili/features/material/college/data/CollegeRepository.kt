package com.buyin.dalili.features.material.college.data

import com.buyin.dalili.features.material.college.domain.model.CollegeModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CollegeRepository @Inject constructor(
    private val collegeDataSource: CollegeDataSource,

    ) {
   fun getMaterial(): Flow<List<CollegeModel>> {
        return collegeDataSource.getMaterial()
    }
}


