package com.houston.docornot.data.impl

import com.houston.docornot.data.dto.LoginRequest
import com.houston.docornot.data.network.NetworkClient
import com.houston.docornot.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val client: NetworkClient
): Repository {
    private val token = ""

    override suspend fun login(portal: String, email: String, password: String): String? {
        val request = LoginRequest(host = portal, userName = email, password = password)
        val response = client.login(request)
        return when (response.statusCode) {
            200 -> token
            else -> null
        }
    }


}