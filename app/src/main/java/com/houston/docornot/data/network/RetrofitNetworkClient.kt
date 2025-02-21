package com.houston.docornot.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.houston.docornot.data.model.LoginRequest
import com.houston.docornot.data.model.LoginResponse
import com.houston.docornot.data.model.LoginResponseInner
import com.houston.docornot.util.Constants.NO_INTERNET_STATUS_CODE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RetrofitNetworkClient @Inject constructor(
    private val context: Context,
    private val service: ApiService
) : NetworkClient {

    override suspend fun login(host: String, request: LoginRequest): LoginResponse {
        if (!isConnected()) {
            val response = LoginResponse(LoginResponseInner("")).apply { statusCode = NO_INTERNET_STATUS_CODE }
            Log.w("TEST", "КОД ОТВЕТА: ${ response.statusCode }")
            return response
        }

        return withContext(Dispatchers.IO) {
            try {
                val response = service.login(host, request)
                Log.i("TEST", "КОД ОТВЕТА: ${ response.statusCode }")
                Log.i("TEST", "ТОКЕН: ${ (response as LoginResponse).response.token }")
                response
            } catch (exception: Throwable) {
                val response = LoginResponse(LoginResponseInner(""))
                Log.e("TEST", "КОД ОТВЕТА: ${ response.statusCode }")
                Log.e("TEST", "НЕИЗВЕСТНАЯ ОШИБКА: ${exception.message}", exception)
                response
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