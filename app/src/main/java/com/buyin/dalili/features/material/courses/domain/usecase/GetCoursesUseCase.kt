package com.buyin.dalili.features.material.courses.domain.usecase

import com.buyin.dalili.features.material.courses.data.CoursesRepository
import com.buyin.dalili.features.material.courses.domain.model.CoursesModel
import com.buyin.dalili.features.material.sources.data.SourcesRepository
import com.buyin.dalili.features.material.sources.domain.model.SourcesModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetCoursesUseCase @Inject constructor(
    private val repository: CoursesRepository
) {

    operator fun invoke(): Flow<List<CoursesModel>> {
        return repository.getCourses()
    }

}
