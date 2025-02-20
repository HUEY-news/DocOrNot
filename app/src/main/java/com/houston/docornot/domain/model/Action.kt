package com.houston.docornot.domain.model

sealed class Action {
    data class Login(val portal: String, val email: String, val password: String) : Action()
}