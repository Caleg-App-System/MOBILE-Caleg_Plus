package com.example.calegplus.data.api

import com.google.gson.annotations.SerializedName


data class RegisterRequest(
    @SerializedName("email")
    var email: String?,
    @SerializedName("username")
    var username: String?,
    @SerializedName("password")
    var password: String?
)