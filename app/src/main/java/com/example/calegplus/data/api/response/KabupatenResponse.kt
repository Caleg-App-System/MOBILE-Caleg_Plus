package com.example.calegplus.data.api.response


import com.google.gson.annotations.SerializedName

data class KabupatenResponse(
    @SerializedName("data")
    val `data`: List<DataXX>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)