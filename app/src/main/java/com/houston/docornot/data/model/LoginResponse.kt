package com.houston.docornot.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("response") val response: LoginResponseInner
) : Response()