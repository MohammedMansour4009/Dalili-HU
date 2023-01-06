package com.buyin.dalili.features.permission.domain.usecase

import com.buyin.dalili.features.permission.data.PermissionRepository
import com.buyin.dalili.features.permission.domain.model.PermissionModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetPermissionUseCase @Inject constructor(
    private val repository: PermissionRepository
) {

    suspend operator fun invoke(teacherId: String): Boolean {
        return repository.getPermission(teacherId)
    }

}
