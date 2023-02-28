package com.example.calegplus.data.api.response


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("email_token")
    val emailToken: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_verified_account")
    val isVerifiedAccount: Boolean,
    @SerializedName("is_verified_role")
    val isVerifiedRole: Boolean,
    @SerializedName("name")
    val name: Any,
    @SerializedName("password")
    val password: String,
    @SerializedName("phone")
    val phone: Any,
    @SerializedName("photo")
    val photo: Any,
    @SerializedName("role")
    val role: Any,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("username")
    val username: String
)