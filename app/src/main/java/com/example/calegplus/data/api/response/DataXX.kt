package com.example.calegplus.data.api.response


import com.google.gson.annotations.SerializedName

data class DataXX(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("province_id")
    val provinceId: String
)