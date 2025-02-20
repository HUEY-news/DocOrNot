package com.houston.docornot.domain.api

import kotlinx.coroutines.flow.Flow

interface Interactor {
    suspend fun login(portal: String, email: String, password: String): Flow<Pair<String?, String?>>
}