package com.example.calegplus.data.api.response


import com.google.gson.annotations.SerializedName

data class DesaResponse(
    @SerializedName("data")
    val `data`: List<DataXXXX>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)