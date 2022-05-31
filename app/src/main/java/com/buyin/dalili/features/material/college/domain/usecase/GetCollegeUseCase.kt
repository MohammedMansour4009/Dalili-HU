package com.buyin.dalili.features.material.college.domain.usecase

import com.buyin.dalili.features.material.college.domain.model.CollegeModel
import com.buyin.dalili.features.material.college.data.CollegeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetCollegeUseCase @Inject constructor(
    private val repository: CollegeRepository
) {

    suspend operator fun invoke(): Flow<List<CollegeModel>> {
        return repository.getMaterial()
    }

}
