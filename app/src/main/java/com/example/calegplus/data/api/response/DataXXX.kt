package com.example.calegplus.data.api.response


import com.google.gson.annotations.SerializedName

data class DataXXX(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("regency_id")
    val regencyId: String
)