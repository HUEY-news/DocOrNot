package com.houston.docornot.data.impl

import android.util.Log
import com.houston.docornot.data.model.LoginRequest
import com.houston.docornot.data.model.LoginResponse
import com.houston.docornot.data.network.NetworkClient
import com.houston.docornot.domain.api.Repository
import com.houston.docornot.util.Constants.BAD_REQUEST_STATUS_CODE
import com.houston.docornot.util.Constants.ERROR_BAD_REQUEST_TEXT
import com.houston.docornot.util.Constants.ERROR_FORBIDDEN_TEXT
import com.houston.docornot.util.Constants.ERROR_LOGIN_TEXT
import com.houston.docornot.util.Constants.ERROR_NO_INTERNET_TEXT
import com.houston.docornot.util.Constants.ERROR_UNAUTHORIZED_TEXT
import com.houston.docornot.util.Constants.FORBIDDEN_STATUS_CODE
import com.houston.docornot.util.Constants.NO_INTERNET_STATUS_CODE
import com.houston.docornot.util.Constants.SUCCESS_STATUS_CODE
import com.houston.docornot.util.Constants.UNAUTHORIZED_STATUS_CODE
import com.houston.docornot.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val client: NetworkClient
) : Repository {

    private var token = ""

    override suspend fun login(portal: String, email: String, password: String): Flow<Resource<String>> = flow {
        val request = LoginRequest(userName = email, password = password)
        val response = client.login(host = portal, request = request)

        when (response.statusCode) {

            SUCCESS_STATUS_CODE -> {
                token = (response as LoginResponse).response.token
                if (token.isNotEmpty()) emit(Resource.Success(token))
                else emit(Resource.Error(ERROR_LOGIN_TEXT))
            }

            BAD_REQUEST_STATUS_CODE -> emit(Resource.Error(ERROR_BAD_REQUEST_TEXT))
            UNAUTHORIZED_STATUS_CODE -> emit(Resource.Error(ERROR_UNAUTHORIZED_TEXT))
            FORBIDDEN_STATUS_CODE -> emit(Resource.Error(ERROR_FORBIDDEN_TEXT))
            NO_INTERNET_STATUS_CODE -> emit(Resource.Error(ERROR_NO_INTERNET_TEXT))
            else -> Log.w("TEST", "НЕИЗВЕСТНАЯ ОШИБКА")
        }
    }
}