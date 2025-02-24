package com.houston.docornot.data.model

import com.google.gson.annotations.SerializedName

data class LoginErrorInner(
    @SerializedName("message") val message: String
)