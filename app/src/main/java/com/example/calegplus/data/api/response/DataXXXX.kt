package com.example.calegplus.data.api.response


import com.google.gson.annotations.SerializedName

data class DataXXXX(
    @SerializedName("district_id")
    val districtId: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)