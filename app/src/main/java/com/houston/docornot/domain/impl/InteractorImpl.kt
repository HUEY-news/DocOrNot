package com.houston.docornot.domain.impl

import com.houston.docornot.domain.api.Interactor
import com.houston.docornot.domain.api.Repository
import com.houston.docornot.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class InteractorImpl @Inject constructor(
    private val repository: Repository
) : Interactor {
    override suspend fun login(portal: String, email: String, password: String): Flow<Pair<String?, String?>> {
        return repository.login(portal, email, password).map { result ->
            when (result) {
                is Resource.Success -> Pair(result.data, null)
                is Resource.Error -> Pair(null, result.message)
            }
        }
    }
}