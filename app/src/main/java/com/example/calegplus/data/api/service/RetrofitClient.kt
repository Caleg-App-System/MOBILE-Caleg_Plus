package com.example.calegplus.data.api.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://caleg-plus.up.railway.app"
    val instance : UserApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl((BASE_URL))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(UserApi::class.java)
    }
}