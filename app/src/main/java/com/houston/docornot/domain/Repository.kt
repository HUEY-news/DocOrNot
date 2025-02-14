package com.houston.docornot.domain

interface Repository {
    suspend fun login(portal: String, email: String, password: String): String?
}