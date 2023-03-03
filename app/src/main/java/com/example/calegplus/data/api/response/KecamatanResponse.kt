package com.example.calegplus.data.api.response


import com.google.gson.annotations.SerializedName

data class KecamatanResponse(
    @SerializedName("data")
    val `data`: List<DataXXX>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)