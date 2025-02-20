package com.houston.docornot.domain.api

import com.houston.docornot.util.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun login(portal: String, email: String, password: String): Flow<Resource<String>>
}