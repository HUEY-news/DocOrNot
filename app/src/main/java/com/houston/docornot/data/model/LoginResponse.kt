package com.houston.docornot.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("response") val response: LoginResponseInner?,
    @SerializedName("error") val error: LoginErrorInner?,
    @SerializedName("statusCode") var statusCode: Int = 0
)