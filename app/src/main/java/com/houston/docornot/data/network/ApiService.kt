package com.houston.docornot.data.network

import com.houston.docornot.data.dto.LoginRequest
import com.houston.docornot.data.dto.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    @POST("api/2.0/authentication")
    suspend fun login(
        @Header("Host") host: String,
        @Body loginRequest: LoginRequest
    ): Response
}