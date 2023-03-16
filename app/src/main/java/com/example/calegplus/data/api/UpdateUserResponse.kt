package com.example.calegplus.data.api

import com.google.gson.annotations.SerializedName

data class UpdateUserResponse(
    @SerializedName("name")
    var name: String?,
    @SerializedName("phone")
    var phone: String?,
    @SerializedName("address")
    var address: String?
)