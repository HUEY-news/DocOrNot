package com.houston.docornot.data.model

import com.google.gson.annotations.SerializedName

open class Response {
    @SerializedName("statusCode") var statusCode: Int = 0
}