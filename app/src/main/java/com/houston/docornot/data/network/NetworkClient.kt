package com.houston.docornot.data.network

import com.houston.docornot.data.dto.Response

interface NetworkClient {
    suspend fun login(dto: Any): Response
}