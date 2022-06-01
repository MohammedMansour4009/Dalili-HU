package com.buyin.dalili.features.material.courses.data

import com.buyin.dalili.features.material.courses.domain.model.CoursesModel
import com.buyin.dalili.features.material.sources.domain.model.SourcesModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoursesRepository @Inject constructor(
    private val coursesDataSource: CoursesDataSource,

    ) {
   fun getCourses(): Flow<List<CoursesModel>> {
        return coursesDataSource.getCourses()
    }
}


