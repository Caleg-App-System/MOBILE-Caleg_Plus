package com.example.calegplus.data.api.response


import com.google.gson.annotations.SerializedName

data class ProvinsiResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)