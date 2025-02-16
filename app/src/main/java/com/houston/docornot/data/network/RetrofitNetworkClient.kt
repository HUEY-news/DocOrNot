package com.houston.docornot.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.houston.docornot.data.dto.LoginRequest
import com.houston.docornot.data.dto.Response
import com.houston.docornot.util.Constants.FORBIDDEN_STATUS_CODE
import com.houston.docornot.util.Constants.NO_INTERNET_STATUS_CODE
import com.houston.docornot.util.Constants.SUCCESS_STATUS_CODE
import com.houston.docornot.util.Constants.UNAUTHORIZED_STATUS_CODE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RetrofitNetworkClient @Inject constructor(
    private val context: Context,
    private val service: ApiService
) : NetworkClient {

    override suspend fun login(dto: Any): Response {
        if (!isConnected()) return Response().apply { statusCode = NO_INTERNET_STATUS_CODE }
        if (dto !is LoginRequest) return Response().apply { statusCode = FORBIDDEN_STATUS_CODE }

        return withContext(Dispatchers.IO) {
            try {
                val response = service.login(dto.host, dto)
                response.apply { statusCode = SUCCESS_STATUS_CODE }
            } catch (exception: Throwable) {
                Response().apply { statusCode = UNAUTHORIZED_STATUS_CODE }
            }
        }
    }

    private fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }
}