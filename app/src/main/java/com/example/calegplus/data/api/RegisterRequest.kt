package com.example.calegplus.data.api

import com.google.gson.annotations.SerializedName


data class RegisterRequest(
    @field:SerializedName("email")
    val email: String? = null,
    @field:SerializedName("username")
    var username: String? = null,
    @field:SerializedName("password")
    var password: String? = null,
    @field:SerializedName("role")
    var role: String? = null,
    @field:SerializedName("address")
    var address: String? = null,
    @field:SerializedName("name")
    var name: String? = null

)