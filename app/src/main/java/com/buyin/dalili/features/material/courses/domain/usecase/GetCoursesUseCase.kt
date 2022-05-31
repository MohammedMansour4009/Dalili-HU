package com.buyin.dalili.features.material.courses.domain.usecase

import com.buyin.dalili.features.material.college.domain.model.CollegeModel
import com.buyin.dalili.features.material.college.data.CollegeRepository
import com.buyin.dalili.features.material.courses.data.CoursesRepository
import com.buyin.dalili.features.material.courses.domain.model.CoursesModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetCoursesUseCase @Inject constructor(
    private val repository: CoursesRepository
) {

    suspend operator fun invoke(): Flow<List<CoursesModel>> {
        return repository.getCourses()
    }

}
