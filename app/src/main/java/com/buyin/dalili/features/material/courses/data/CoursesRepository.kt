package com.buyin.dalili.features.material.courses.data

import com.buyin.dalili.features.material.college.domain.model.CollegeModel
import com.buyin.dalili.features.material.courses.domain.model.CoursesModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoursesRepository @Inject constructor(
    private val collegeDataSource: CoursesDataSource,

    ) {
   fun getCourses(): Flow<List<CoursesModel>> {
        return collegeDataSource.getCourses()
    }
}


