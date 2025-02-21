package com.houston.docornot.data.network

import com.houston.docornot.data.model.LoginRequest
import com.houston.docornot.data.model.LoginResponse

interface NetworkClient {
    suspend fun login(host: String, request: LoginRequest): LoginResponse
}