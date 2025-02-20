package com.houston.docornot.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponseInner(
    @SerializedName("token") val token: String
)
