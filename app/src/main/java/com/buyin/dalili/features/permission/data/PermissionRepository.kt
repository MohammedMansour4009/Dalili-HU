package com.buyin.dalili.features.permission.data

import com.buyin.dalili.features.permission.domain.model.PermissionModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PermissionRepository @Inject constructor(
    private val collegeDataSource: PermissionDataSource,

    ) {
   suspend fun getPermission(teacherId: String): Boolean {
        return collegeDataSource.getPermission(teacherId)
    }
}


