package com.houston.docornot.data.impl

import com.houston.docornot.data.dto.LoginRequest
import com.houston.docornot.data.dto.LoginResponse
import com.houston.docornot.data.network.NetworkClient
import com.houston.docornot.domain.Repository
import com.houston.docornot.util.Constants.ERROR_FORBIDDEN_TEXT
import com.houston.docornot.util.Constants.ERROR_INTERNET_TEXT
import com.houston.docornot.util.Constants.ERROR_UNAUTHORIZED_TEXT
import com.houston.docornot.util.Constants.FORBIDDEN_STATUS_CODE
import com.houston.docornot.util.Constants.NO_INTERNET_STATUS_CODE
import com.houston.docornot.util.Constants.SUCCESS_STATUS_CODE
import com.houston.docornot.util.Constants.UNAUTHORIZED_STATUS_CODE
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val client: NetworkClient
) : Repository {
    private var token = ""
    private var errorMessage = ""

    override suspend fun login(portal: String, email: String, password: String) {

        val request = LoginRequest(host = portal, userName = email, password = password)
        val response = client.login(request)

        when (response.statusCode) {
            SUCCESS_STATUS_CODE -> token = (response as LoginResponse).token
            UNAUTHORIZED_STATUS_CODE -> errorMessage = ERROR_UNAUTHORIZED_TEXT
            FORBIDDEN_STATUS_CODE -> errorMessage = ERROR_FORBIDDEN_TEXT
            NO_INTERNET_STATUS_CODE -> errorMessage = ERROR_INTERNET_TEXT
        }
    }
}