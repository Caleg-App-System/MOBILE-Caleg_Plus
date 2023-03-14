package com.example.calegplus.data.api.service

import com.example.calegplus.data.api.LoginRequest
import com.example.calegplus.data.api.RegisterRequest
import com.example.calegplus.data.api.response.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {

    @POST("/auth/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<AuthResponse>

    @POST("/auth/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): Response<AuthResponse>

    @GET("/auth/getbyid/{id}")
    fun getUserProfile(@Path("id")id: Int): Call <GetUserResponse>

    @GET("/provinsi/getall/json")
    fun getProv(): Call<ProvinsiResponse>

    @GET("kabupaten/get/{id}")
    fun getKab(@Path("id") id: String): Call<KabupatenResponse>

    @GET("kecamatan/get/{id}")
    fun getKec(@Path("id") id: String): Call<KecamatanResponse>

    @GET("desa/get/{id}")
    fun getDesa(@Path("id") id: String): Call<DesaResponse>
}
