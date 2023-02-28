package com.example.calegplus.data.api

import com.google.gson.annotations.SerializedName

data class GetUserResponse(
    @field:SerializedName("id")
    val id: String? = null,
    @field:SerializedName("username")
    val username: String? = null,
    @field:SerializedName("email")
    val email: String? = null,
    @field:SerializedName("password")
    val password: String? = null
)