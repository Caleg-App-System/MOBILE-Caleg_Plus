package com.example.calegplus.data.api.response


import com.google.gson.annotations.SerializedName

data class GetUserResponse(
    @SerializedName("data")
    val `data`: DataXXXXX,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)