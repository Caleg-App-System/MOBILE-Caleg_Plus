package com.example.calegplus.data.api.response


import com.google.gson.annotations.SerializedName

data class DataXXXXX(
    @SerializedName("address")
    val address: Any,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("email")
    val email: Any,
    @SerializedName("email_token")
    val emailToken: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_archived")
    val isArchived: Any,
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
    @SerializedName("photo_ktp")
    val photoKtp: Any,
    @SerializedName("role")
    val role: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("working_area")
    val workingArea: Any
)