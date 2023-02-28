package com.example.calegplus.data.api

import com.google.gson.annotations.SerializedName

data class PostUserResponse(

    @field:SerializedName("username")
    val username: String? = null,
    @field:SerializedName("email")
    val email: String? = null,
    @field:SerializedName("password")
    val password: String? = null
)