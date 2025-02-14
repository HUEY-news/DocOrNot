package com.houston.docornot.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.houston.docornot.data.dto.LoginRequest
import com.houston.docornot.data.dto.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RetrofitNetworkClient @Inject constructor(
    private val context: Context,
    private val service: ApiService
) : NetworkClient {

    override suspend fun login(dto: Any): Response {
        if (!isConnected()) return Response().apply { statusCode = -1 }
        if (dto !is LoginRequest) return Response().apply { statusCode = 403 }

        return withContext(Dispatchers.IO) {
            try {
                Log.i("TEST", "Отправка запроса: $dto")
                val response = service.login(dto.host, dto)
                Log.i("TEST", "Ответ от сервера: ${response}")
                response.apply { statusCode = 200 }
            } catch (exception: Throwable) {
                Log.e("TEST", "Ошибка при запросе: ${exception.message}", exception)
                Response().apply { statusCode = 401 }
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