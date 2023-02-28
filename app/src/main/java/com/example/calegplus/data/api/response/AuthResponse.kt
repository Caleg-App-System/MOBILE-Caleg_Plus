package com.example.calegplus.data.api.response


import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("data")
    val `data`: DataX,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("token")
    val token: String
)